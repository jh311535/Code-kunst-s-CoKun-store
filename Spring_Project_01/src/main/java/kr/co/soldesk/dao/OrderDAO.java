package kr.co.soldesk.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import kr.co.soldesk.beans.OrderBean;
import kr.co.soldesk.mapper.OrderMapper;

@Repository
public class OrderDAO {
    @Autowired
    private OrderMapper orderMapper;

    public void addOrder(OrderBean order) {
        orderMapper.addOrder(order);
    }

    public List<OrderBean> getOrdersByUserId(int user_idx) {
        return orderMapper.getOrdersByUserId(user_idx);
    }
    
    public int getOrderNumberSeq() {
        return orderMapper.getOrderNumberSeq();
    }

    public void insertOrder(OrderBean order) {
    	orderMapper.insertOrder(order);
    }
}
