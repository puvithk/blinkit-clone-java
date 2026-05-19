package cart.dao.impl;

import cart.dao.CartDao;
import cart.model.Cart;
import mockData.MockData;
import user.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryCartDaoImpl implements CartDao {
    private static final List<Cart> carts = MockData.carts;
    @Override
    public Cart findCartByUser(User user) {
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
    public Cart findCartByUserId(Integer userId) {

        return carts.stream()
                .filter(cart -> Objects.equals(cart.getUser().getId() , userId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void clearCartItems(Cart cart) {
        int index = carts.indexOf(cart);
        cart.setCartItems(new ArrayList<>());
        cart.setTotalAmount(0.0);
        carts.set(index , cart);

    }

}
