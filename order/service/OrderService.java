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
import order.model.Order;
import order.model.OrderItem;
import order.model.enums.OrderStatus;
import order.model.enums.PaymentMethod;
import product.exception.ProductNotFound;
import product.model.Product;
import product.service.ProductService;
import user.model.User;
import user.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    // User service
    private final UserService userService = new UserService();
    // Get the Cart service
    private final CartService cartService = new CartService();
    // Get the product services
    private final ProductService productService = new ProductService();
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
                .allMatch(product -> warehouseInventoryService.isProductAvailable(warehouseId , product.getProduct() ));
        if(!productAvailability){
            throw new ProductNotFound("Product not Available");
        }
        // Get the order from the helper fucntion
        User user = userService.getUserByUserId(userId);
        WareHouse wareHouse = warehouseService.getWarehouseById(warehouseId);
        Order order = createOrderHelper(user , wareHouse , cart);
        // Create a order
        OrderDao.createOrder(order);
        // return the OrderPlaced object
        return new CustomResponse<>(true ,
                "Order created successfully" ,
                new OrderPlacedResponse(order.getId() ,
                        cart.getTotalAmount() ,
                        14 // random times
                        , "random" // random addres
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
            orderItems.add(new OrderItem(cartItem.getProduct(), cartItem.getQuantity(), cartItem.getProduct().getPrice()
                    )
            );
        }

        order.setOrderItems(orderItems);



        return order;
        }


    }
}
