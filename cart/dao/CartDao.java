package cart.dao;

import cart.model.Cart;
import user.model.User;

public interface CartDao {
    Cart findCartByUser(User user);

    Cart createCart(User user);

    void updateCart(Cart cart);

    Cart findCartByUserId(Integer userId);

    void clearCartItems(Cart cart);
}
