package service;

import dao.CartDAO_admin;
import dto.CartDTO_admin;
import filter.CartFilterDTO_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService_admin {

    @Autowired
    private CartDAO_admin cartDAO;

    public CartDTO_admin getCartById(int cart_id) {
        return cartDAO.getCartById(cart_id);
    }

    public List<CartDTO_admin> getAllCarts() {
        return cartDAO.getAllCarts();
    }

    public void updateCart(CartDTO_admin cart) {
        cartDAO.updateCart(cart);
    }

    public void deleteCart(int cart_id) {
        cartDAO.deleteCart(cart_id);
    }

    public List<CartDTO_admin> getCartsByFilterAndSort(CartFilterDTO_admin filter) {
        return cartDAO.getCartsByFilterAndSort(filter);
    }

    public int getCartCountByFilter(CartFilterDTO_admin filter) {
        return cartDAO.getCartCountByFilter(filter);
    }
}
