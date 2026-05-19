package cart.controller;

import cart.model.Cart;
import cart.service.CartService;
import product.model.Product;

public class CartController {
    // Get the cart service layer
    private final CartService cartService = new CartService();
    public void addToMyCart(Product product) {
        // Adding product to the cart
         cartService.addToMyCart(product);
    }

    public Cart getCartItems() {
        // Get the cart items

        return cartService.getCartItems();
    }
}
