package kr.co.soldesk.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.soldesk.beans.CartBean;
import kr.co.soldesk.dao.CartDAO;
import kr.co.soldesk.mapper.CartMapper;

@Service
public class CartService {
    @Autowired
    private CartMapper cartMapper;
    
    @Autowired
    private CartDAO cartDAO;

    public boolean addToCart(CartBean cartBean) {
        int count = cartMapper.checkBookInCart(cartBean.getUser_idx(), cartBean.getBook_id());
        if (count > 0) {
            return false; // 책이 이미 장바구니에 있음
        } else {
            cartMapper.addToCart(cartBean);
            return true; // 책이 장바구니에 추가됨
        }
    }

    public List<CartBean> getUserCart(int user_idx) {
        return cartMapper.getUserCart(user_idx);
    }

    public void removeFromCart(int cart_id) {
        cartMapper.removeFromCart(cart_id);
    }

    public void updateCartQuantity(int cart_id, int cart_quantity) {
        cartMapper.updateCartQuantity(cart_id, cart_quantity);
    }
    
    public List<CartBean> getCartItemsByIds(List<Integer> cartIds) {
        return cartMapper.getCartItemsByIds(cartIds);
    }
    
    public void removeSelected(List<Integer> cartIds, int user_idx) {
    	for (int cart_id : cartIds) {
            cartMapper.removeSelected(cart_id, user_idx);
        }
    }
    public List<CartBean> getCartList(int userIdx) {
        return cartDAO.getCartList(userIdx);
    }
    
    public List<CartBean> getCartItemsByUserId(int userId) {
        return cartMapper.getCartItemsByUserId(userId);
    }
}
