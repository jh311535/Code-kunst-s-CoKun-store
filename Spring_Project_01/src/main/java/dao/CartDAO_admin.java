package dao;

import dto.CartDTO_admin;
import filter.CartFilterDTO_admin;
import mapper.CartMapper_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDAO_admin {

    @Autowired
    private CartMapper_admin cartMapper;

    public CartDTO_admin getCartById(int cart_id) {
        return cartMapper.getCartById(cart_id);
    }

    public List<CartDTO_admin> getAllCarts() {
        return cartMapper.getAllCarts();
    }

    public void updateCart(CartDTO_admin cart) {
        cartMapper.updateCart(cart);
    }

    public void deleteCart(int cart_id) {
        cartMapper.deleteCart(cart_id);
    }

    public List<CartDTO_admin> getCartsByFilterAndSort(CartFilterDTO_admin filter) {
        return cartMapper.getCartsByFilterAndSort(filter);
    }

    public int getCartCountByFilter(CartFilterDTO_admin filter) {
        return cartMapper.getCartCountByFilter(filter);
    }
}
