package kr.co.soldesk.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.soldesk.beans.CartBean;
import kr.co.soldesk.beans.OrderBean;
import kr.co.soldesk.dao.CartDAO;
import kr.co.soldesk.dao.OrderDAO;
import kr.co.soldesk.mapper.OrderMapper;

@Service
public class OrderService {
    @Autowired
    private OrderDAO orderDao;
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private BookService bookService;


    public List<OrderBean> getOrdersByUserId(int user_idx) {
        return orderDao.getOrdersByUserId(user_idx);
    }
    
    public int processOrder(List<CartBean> cartList, OrderBean orderDetails) {
    	int orderNumber = orderMapper.getOrderNumberSeq();  // 새로운 주문 번호 생성
        orderDetails.setOrder_number(orderNumber);
    	for (CartBean cartItem : cartList) {
            orderDetails.setBook_id(cartItem.getBook_id());
            orderDetails.setOrder_quantity(cartItem.getCart_quantity());
            Integer bookPrice = bookService.getBookPrice(cartItem.getBook_id());
            orderDetails.setOrder_price(bookPrice * cartItem.getCart_quantity());
            orderMapper.insertOrder(orderDetails);
        }
    	
    	return orderNumber;
    }
    
    public List<OrderBean> getOrderDetails(String orderNumber) {
        return orderMapper.getOrderDetails(orderNumber);
    }
    
    public List<OrderBean> getUserOrderList(int user_idx) {
        return orderMapper.getUserOrderList(user_idx);
    }
    
    public List<OrderBean> getOrderDetailById(int orderNumber, int bookId) {
        return orderMapper.getOrderDetailByid(orderNumber, bookId);
    }
    
    public void cancelOrder(String orderNumber, int bookId, String cancelReason) throws Exception {
        // 주문 상태 업데이트 로직
        orderMapper.updateOrderStatus(orderNumber, bookId, cancelReason);
    }
}
