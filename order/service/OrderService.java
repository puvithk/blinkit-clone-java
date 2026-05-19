package order.service;

import cart.model.Cart;
import cart.model.CartItem;
import cart.service.CartService;
import common.response.CustomResponse;
import common.security.SecurityContext;
import inventory.model.WareHouse;
import inventory.service.WarehouseInventoryService;
import inventory.service.WarehouseService;
import inventory.service.impl.WarehouseInventoryServiceImpl;
import inventory.service.impl.WarehouseServiceImpl;
import order.dao.OrderDao;
import order.dao.impl.InMemoryOrderDaoImpl;
import order.dto.OrderPlacedResponse;
import order.dto.OrderResponse;
import order.exception.OrderNotFoundException;
import order.model.Order;
import order.model.OrderItem;
import order.model.enums.OrderStatus;
import product.exception.ProductNotFound;
import user.model.User;
import user.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    // User service
    private final UserService userService = new UserService();
    // Get the Cart service
    private final CartService cartService = new CartService();

    // Get the warehouse service
    private final WarehouseInventoryService warehouseInventoryService = new WarehouseInventoryServiceImpl();

    // Warehouse service
    private final WarehouseService warehouseService = new WarehouseServiceImpl();
    // Get order dao
    private final OrderDao orderDao = new InMemoryOrderDaoImpl();
    public CustomResponse<OrderPlacedResponse> placeOrderByUser() {
        // Get the user details in security context
        Integer userId = SecurityContext.getContext().getUserId();
        // Get the warehouse Id
        Integer warehouseId = SecurityContext.getContext().getWarehouseId();
        // Get the cart based on the user
        Cart cart = cartService.getCartItemByUserId(userId);
        // Check weather the products is available or not
        boolean productAvailability = cart
                .getCartItems()
                .stream()
                .allMatch(product -> warehouseInventoryService.isProductAvailable(warehouseId ,
                        product.getProduct() ));
        if(!productAvailability){
            throw new ProductNotFound("Product not Available");
        }
        // Get the order from the helper function
        User user = userService.getUserByUserId(userId);
        //Get the warehouse using Id
        WareHouse wareHouse = warehouseService.getWarehouseById(warehouseId);
        // Create a order object
        Order order = createOrderHelper(user , wareHouse , cart);
        // Create a order
        orderDao.createOrder(order);
        // return the OrderPlaced object
        return new CustomResponse<>(true ,
                "Order created successfully" ,
                new OrderPlacedResponse(order.getId() ,
                        cart.getTotalAmount() ,
                        14 // random times
                        , "random" // random adders
                        , OrderStatus.PENDING_PAYMENT ,
                        null));
    }

    // Helper function to create a order
    public Order createOrderHelper(User user , WareHouse wareHouse , Cart cart ){
        Order order = new Order();
        // Set user to the order
        order.setUser(user);
        // Get the warehouse details
        order.setWareHouse(wareHouse);
        // Set the list of products

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getCartItems()) {
            orderItems.add(new OrderItem(cartItem.getProduct(),
                    cartItem.getQuantity(),
                    cartItem.getProduct().getPrice()
                    )
            );
        }

        order.setOrderItems(orderItems);

        order.setTotalAmount(cart.getTotalAmount());

        return order;
        }


    public CustomResponse<OrderResponse> getOrderById(Integer orderId) {
        Order order = orderDao.findOrderById(orderId);
        if(order==null){
            throw new OrderNotFoundException("Order not found");
        }
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getId());
        orderResponse.setTotalAmount(order.getTotalAmount());
        orderResponse.setOrderStatus(order.getOrderStatus());
        orderResponse.setPaymentMethod(order.getPaymentMethod());
        orderResponse.setDeliveryAddress(order.getDeliveryAddress());
        orderResponse.setEstimatedDeliveryTime(order.getEstimatedDeliveryTime());
        orderResponse.setOrderedAt(order.getOrderedAt());
        return new CustomResponse<>(
                true ,
                "Order present" ,
                orderResponse
        );
    }

    public void updateOrderStatus(Integer orderId, OrderStatus orderStatus) {
        // Get the order
        Order order = orderDao.findOrderById(orderId);
        if(order==null){
            throw new OrderNotFoundException("Order not found");
        }

        // Update the order
        orderDao.updateOrderStatus(order , orderStatus);


    }

    public CustomResponse<List<OrderResponse>> getAllOrderByUser() {
        // Get the user id
        Integer userId = SecurityContext.getContext().getUserId();
        // Get all the Orders from the DAO
        List<Order> orderList = orderDao.findAllOrderByUserId(userId);
        List<OrderResponse> orderResponses = orderList.stream().map(order -> {
                    OrderResponse response = new OrderResponse();
                    response.setOrderId(order.getId());
                    response.setTotalAmount(order.getTotalAmount());
                    response.setOrderStatus(order.getOrderStatus());
                    response.setPaymentMethod(order.getPaymentMethod());
                    response.setDeliveryAddress(order.getDeliveryAddress());
                    response.setEstimatedDeliveryTime(order.getEstimatedDeliveryTime());
                    response.setOrderedAt(order.getOrderedAt());
                    return response;
                        }).toList();
        // return all the list
        return new CustomResponse<>(
                true ,
                "Fetched Orders" ,
                orderResponses
        );
    }

    public Order findOrderById(Integer orderId) {
        Order order = orderDao.findOrderById(orderId);
        if(order==null){
            throw new OrderNotFoundException("Order not found");
        }
        return order;
    }
}

