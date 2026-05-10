package cart.dao.impl;

import cart.dao.CartDao;
import cart.model.Cart;
import user.model.User;

import java.util.ArrayList;
import java.util.List;

public class inMemoryCartDaoImpl implements CartDao {
    private List<Cart> carts = new ArrayList<>(List.of());
    @Override
    public Cart getCartByUser(User user) {
        return carts
                .stream()
                .filter(cart -> cart.getUser().equals(user))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Cart createCart(User user) {
        int lastId = carts.isEmpty() ? 0 : carts.getLast().getId();
        Cart newCart = new Cart();
        newCart.setId(lastId+1);
        newCart.setUser(user);
        newCart.setTotalAmount(0.0);
        newCart.setCartItems(new ArrayList<>());
        carts.add(newCart);
        return newCart;
    }

    @Override
    public void updateCart(Cart cart) {
        // Get the index of cart
        int index = -1;

        for (int i = 0; i < carts.size(); i++) {

            if (carts.get(i).getId().equals(cart.getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            carts.set(index, cart);
        }
    }

    @Override
    public Cart findCartByUser(Integer userId) {
        return carts.stream()
                .filter(cart -> cart.getUser().getId() == userId)
                .findFirst()
                .orElse(null);
    }

}
