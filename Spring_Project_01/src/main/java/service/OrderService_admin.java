package service;

import dao.OrderDAO_admin;
import dto.OrderDTO_admin;
import filter.OrderFilterDTO_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService_admin {

    @Autowired
    private OrderDAO_admin orderDAO;

    public OrderDTO_admin getOrderById(int order_id) {
        return orderDAO.getOrderById(order_id);
    }

    public List<OrderDTO_admin> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    public void updateOrder(OrderDTO_admin order) {
        orderDAO.updateOrder(order);
    }

    public void deleteOrder(int order_id) {
        orderDAO.deleteOrder(order_id);
    }

    public List<OrderDTO_admin> getOrdersByFilterAndSort(OrderFilterDTO_admin filter) {
        return orderDAO.getOrdersByFilterAndSort(filter);
    }

    public int getOrderCountByFilter(OrderFilterDTO_admin filter) {
        return orderDAO.getOrderCountByFilter(filter);
    }
}
