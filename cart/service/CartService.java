package cart.service;

import cart.dao.CartDao;

import cart.dao.impl.InMemoryCartDaoImpl;
import cart.exception.CartNotFoundException;
import cart.exception.ProductOutOfStockException;
import cart.model.Cart;
import cart.model.CartItem;
import common.security.SecurityContext;
import inventory.exception.WareHouseNotAvaliable;
import inventory.service.WarehouseInventoryService;
import inventory.service.impl.WarehouseInventoryServiceImpl;
import product.model.Product;
import user.model.User;
import user.service.UserService;

public class CartService {
    private final WarehouseInventoryService warehouseInventoryService = new WarehouseInventoryServiceImpl();
    // Get the suer service top find the user
    private final UserService userService = new UserService();

    // Get the cart dao
    private final CartDao cartDao = new InMemoryCartDaoImpl();

    public void addToMyCart(Product product) {
        // Check is the product is in stock in the current warehouse
        // Get the warehouse details
        Integer warehouseId = SecurityContext.getContext().getWarehouseId();
        boolean isAvailable =  warehouseInventoryService.isProductAvailable(warehouseId , product);
        System.out.println("isAvailable" + isAvailable);
        if(!isAvailable){
                throw new ProductOutOfStockException("Product out of stock");
        }

        Integer userId = SecurityContext.getContext().getUserId();

        // Get the user details
        User user = userService.getUserByUserId(userId);
        // Get or create the cart of the user
        Cart cart = cartDao.findCartByUser(user);
        // Check if cart is null
        // If null Create a cart
        if(cart==null){
            cart = cartDao.createCart(user);
        }
        // Add the product to the already available list
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        // Update in the list
        cart.getCartItems().add(cartItem);
        cart.setTotalAmount(cart.getTotalAmount() +  product.getPrice());

        cartDao.updateCart(cart);


        System.out.println(cart.toString());

    }

    public Cart getCartItems() {

        // Get the user id
        Integer userId = SecurityContext.getContext().getUserId();

        // Get the database on the user id
        Cart cart =  cartDao.findCartByUserId(userId);
        if(cart==null){
            throw new CartNotFoundException("Cart not found");
        }
        return cart;
    }
}
