package cart.dao;

import cart.model.Cart;
import user.model.User;

public interface CartDao {
    Cart getCartByUser(User user);

    Cart createCart(User user);

    void updateCart(Cart cart);
}
