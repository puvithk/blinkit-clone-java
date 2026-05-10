package cart.controller;

import cart.model.Cart;
import cart.service.CartService;
import product.model.Product;

public class CartController {
    // Get the cart service layer
    private final CartService cartService = new CartService();
    public void addToMyCart(Product product) {
        cartService.addToMyCart(product);
    }

    public Cart getCartItems() {
        return cartService.getCartItems();
    }
}
