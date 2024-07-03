package dao;

import dto.OrderDTO_admin;
import filter.OrderFilterDTO_admin;
import mapper.OrderMapper_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAO_admin {

    @Autowired
    private OrderMapper_admin orderMapper;

    public OrderDTO_admin getOrderById(int order_id) {
        return orderMapper.getOrderById(order_id);
    }

    public List<OrderDTO_admin> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    public void updateOrder(OrderDTO_admin order) {
        orderMapper.updateOrder(order);
    }

    public void deleteOrder(int order_id) {
        orderMapper.deleteOrder(order_id);
    }

    public List<OrderDTO_admin> getOrdersByFilterAndSort(OrderFilterDTO_admin filter) {
        return orderMapper.getOrdersByFilterAndSort(filter);
    }

    public int getOrderCountByFilter(OrderFilterDTO_admin filter) {
        return orderMapper.getOrderCountByFilter(filter);
    }
}
