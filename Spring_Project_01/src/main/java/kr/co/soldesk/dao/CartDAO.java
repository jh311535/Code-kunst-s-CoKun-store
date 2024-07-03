package kr.co.soldesk.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.soldesk.beans.CartBean;
import kr.co.soldesk.mapper.CartMapper;
@Repository
public class CartDAO {
	@Autowired
	private CartMapper cartMapper;

	public List<CartBean> getCartList(int user_idx) {
		return cartMapper.getCartList(user_idx);
	}
}
