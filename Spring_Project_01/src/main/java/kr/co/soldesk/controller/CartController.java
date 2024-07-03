package kr.co.soldesk.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.soldesk.beans.CartBean;
import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.service.CartService;
import kr.co.soldesk.service.UserService;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
	private UserService userService;

    
    @PostMapping("/add")
    @ResponseBody
    public String addToCart(@RequestParam int user_idx, @RequestParam int book_id, @RequestParam int cart_quantity) {
        try {
        	CartBean cart = new CartBean();
            cart.setUser_idx(user_idx);
            cart.setBook_id(book_id);
            cart.setCart_quantity(cart_quantity);
            boolean added = cartService.addToCart(cart);
            if (added) {
                return "2";
            } else {
                return "1";
            }
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
    	UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");
		UserBean user = userService.getUserInfo(loginUserBean);
        List<CartBean> cartList = cartService.getUserCart(user.getUser_idx());
        model.addAttribute("cartList", cartList);
        model.addAttribute("user_idx", user.getUser_idx());
        return "cart/cart";
    }

    @PostMapping("/remove")
    @ResponseBody
    public String removeFromCart(@RequestParam int cart_id) {
        cartService.removeFromCart(cart_id);
        return "Item removed from cart";
    }
    
    @PostMapping("/removeSelected")
    @ResponseBody
    public String removeSelected(@RequestParam("cart_ids") List<Integer> cart_ids, @RequestParam int user_idx) {
         	
    	System.out.println("Received cart_ids: " + cart_ids); // 로그 추가
        System.out.println("Received user_idx: " + user_idx); // 로그 추가
    	
        cartService.removeSelected(cart_ids, user_idx);
        return "Selected item removed";
        	       
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateCartQuantity(@RequestParam int cart_id, @RequestParam int cart_quantity) {
        cartService.updateCartQuantity(cart_id, cart_quantity);
        return "Cart updated";
    }
    
}