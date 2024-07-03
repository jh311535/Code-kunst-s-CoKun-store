package kr.co.soldesk.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.soldesk.beans.BookDTO;
import kr.co.soldesk.beans.CartBean;
import kr.co.soldesk.beans.OrderBean;
import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.service.OrderService;
import kr.co.soldesk.service.UserService;
import kr.co.soldesk.service.BookService;
import kr.co.soldesk.service.CartService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;

	@Autowired
	private CartService cartService;

	@PostMapping("/checkout")
	public String checkout(@RequestParam(value = "book_ids", required = false) List<Integer> book_ids,
			@RequestParam(value = "order_quantities", required = false) List<Integer> order_quantities,
			@RequestParam(value = "cart_ids", required = false) List<Integer> cart_ids,
			@RequestParam(value = "book_id", required = false) Integer book_id,
			@RequestParam(value = "order_quantity", required = false) Integer order_quantity,
			@ModelAttribute("orderBean") OrderBean orderBean, HttpSession session, Model model) {

		UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");
		if (loginUserBean == null) {
			return "user/not_login";
		}

		UserBean user = userService.getUserInfo(loginUserBean);
		orderBean.setUser_idx(user.getUser_idx());

		if (book_id != null && order_quantity != null) {
			// 단일 구매
			orderBean.setBook_id(book_id);
			orderBean.setOrder_quantity(order_quantity);
			model.addAttribute("book", bookService.getBookById(book_id));
		} else if (book_ids != null && order_quantities != null && cart_ids != null && !book_ids.isEmpty()
				&& !order_quantities.isEmpty() && !cart_ids.isEmpty()) {
			// 장바구니 구매
			orderBean.setBook_ids(book_ids);
			orderBean.setOrder_quantities(order_quantities);
			orderBean.setCart_ids(cart_ids);

			List<BookDTO> books = new ArrayList<>();
			for (int i = 0; i < book_ids.size(); i++) {
				BookDTO book = bookService.getBookById(book_ids.get(i));
				book.setQuantity(order_quantities.get(i));
				books.add(book);
			}
			model.addAttribute("books", books);
		} else {
			// 필요한 경우 기본 에러 처리
			return "redirect:/error"; // 에러 페이지로 리디렉션
		}

		model.addAttribute("user", user);
		model.addAttribute("orderBean", orderBean);
		return "order/checkout";
	}

	@PostMapping("/submit")
	public ResponseEntity<Map<String, Object>> submitOrder(@RequestParam("imp_uid") String impUid,
			@RequestParam("merchant_uid") String merchantUid, @RequestParam("paid_amount") int paidAmount,
			@RequestParam("ordererName") String ordererName, @RequestParam("addressnum") String addressnum,
			@RequestParam("address") String address, @RequestParam("detailaddress") String detailaddress,
			@RequestParam("phone") String phone, @RequestParam("email") String email,
			@RequestParam("request") String request, @RequestParam("request_text") String requestText,
			@RequestParam("user_idx") int userIdx, @RequestParam(value = "book_id", required = false) Integer bookId,
			@RequestParam(value = "order_quantity", required = false) Integer orderQuantity,
			@RequestParam(value = "book_ids", required = false) String bookIds,
			@RequestParam(value = "order_quantities", required = false) String orderQuantities,
			@RequestParam(value = "cart_ids", required = false) String cartIds) {

		int orderNumber = 0;

		try {
			// 주문 정보 생성
			OrderBean orderDetails = new OrderBean();
			orderDetails.setUser_idx(userIdx);
			orderDetails.setOrderer_name(ordererName);
			orderDetails.setAddressnum(addressnum);
			orderDetails.setAddress(address.length() > 40 ? address.substring(0, 40) : address);
			orderDetails.setDetailaddress(detailaddress);
			orderDetails.setPhone(phone);
			orderDetails.setEmail(email);
			orderDetails.setRequest(request);
			orderDetails.setRequest_text(requestText);
			orderDetails.setOrder_date(new Date());
			orderDetails.setDelivery_state("배송 준비중");

			if (bookId != null && bookId != 0 && orderQuantity != null && orderQuantity != 0) {
				// 단일 책 구매
				CartBean cartItem = new CartBean();
				cartItem.setBook_id(bookId);
				cartItem.setCart_quantity(orderQuantity);
				List<CartBean> singleItemList = new ArrayList<>();
				singleItemList.add(cartItem);
				orderNumber = orderService.processOrder(singleItemList, orderDetails);

				List<CartBean> cartList = cartService.getCartItemsByUserId(userIdx);
				for (CartBean cart : cartList) {
					if (cart.getBook_id() == bookId) {
						cartService.removeFromCart(cart.getCart_id());
						break;
					}
				}
			} else if (cartIds != null && !cartIds.isEmpty()) {
				// 장바구니 구매
				// String으로 받은 bookIds와 orderQuantities를 List<Integer>로 변환
				List<Integer> bookIdList = Arrays.asList(bookIds.replaceAll("\\[|\\]", "").split(",")).stream()
						.map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
				List<Integer> orderQuantityList = Arrays.asList(orderQuantities.replaceAll("\\[|\\]", "").split(","))
						.stream().map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
				List<Integer> cartIdList = Arrays.asList(cartIds.replaceAll("\\[|\\]", "").split(",")).stream()
						.map(String::trim).map(Integer::parseInt).collect(Collectors.toList());

				List<CartBean> cartList = cartService.getCartItemsByIds(cartIdList);
				for (int i = 0; i < cartList.size(); i++) {
					cartList.get(i).setBook_id(bookIdList.get(i));
					cartList.get(i).setCart_quantity(orderQuantityList.get(i));
				}

				orderNumber = orderService.processOrder(cartList, orderDetails);
				// 구매한 장바구니 항목 제거
				for (Integer cartId : cartIdList) {
					cartService.removeFromCart(cartId);
				}
			}
			Map<String, Object> response = new HashMap<>();
			response.put("order_number", orderNumber);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/order_success")
	public String orderSuccess(Model model, @RequestParam("order_number") String orderNumber) {
		// 주문 번호로 주문 정보를 조회
		List<OrderBean> orderDetails = orderService.getOrderDetails(orderNumber);

		model.addAttribute("orderDetails", orderDetails);

		return "order/order_success";
	}

	@GetMapping("/order_list")
	public String orderList(HttpSession session, Model model) {
		UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");

		if (loginUserBean == null) {
			return "redirect:/user/login"; // 로그인이 안되어 있을 경우 로그인 페이지로 리다이렉트
		}

		List<OrderBean> orderList = orderService.getUserOrderList(loginUserBean.getUser_idx());
		model.addAttribute("orderList", orderList);

		return "order/order_list";
	}

	@GetMapping("/order_detail")
	public String orderDetail(@RequestParam("order_number") int orderNumber, @RequestParam("book_id") int bookId,
			Model model) {
		List<OrderBean> orderDetails = orderService.getOrderDetailById(orderNumber, bookId);

		model.addAttribute("orderDetails", orderDetails);
		return "order/order_detail";
	}

	@PostMapping("/cancel")
	public ResponseEntity<String> cancelOrder(@RequestParam("order_number") String orderNumber,
			@RequestParam("book_id") int bookId, @RequestParam("cancel_reason") String cancelReason) {
		try {
			orderService.cancelOrder(orderNumber, bookId, cancelReason);
			return ResponseEntity.ok("주문이 취소되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("주문 취소에 실패하였습니다.");
		}
	}
}