package kr.co.soldesk.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.soldesk.beans.OrderBean;

public interface OrderMapper {
    @Insert("INSERT INTO customer_order (order_id, user_idx, book_id, order_quantity, order_price, orderer_name, addressnum, address, detailaddress, phone, email, request, delivery_state, order_date) " +
            "VALUES (order_seq.nextval, #{user_idx}, #{book_id}, #{order_quantity}, #{order_price}, #{orderer_name} #{addressnum}, #{address}, #{detailaddress}, #{phone}, #{email}, #{request}, #{delivery_state}, sysdate)")
    void addOrder(OrderBean order);

    @Select("SELECT * FROM customer_order WHERE user_idx = #{user_idx}")
    List<OrderBean> getOrdersByUserId(int user_idx);
    
    @Select("SELECT order_number_seq.NEXTVAL FROM DUAL")
    int getOrderNumberSeq();

    @Insert("INSERT INTO customer_order (order_id, user_idx, book_id, order_number, order_quantity, order_price, orderer_name, addressnum, address, detailaddress, phone, email, request, request_text, delivery_state, order_date) " +
            "VALUES (customer_order_seq.NEXTVAL, #{user_idx}, #{book_id}, #{order_number}, #{order_quantity}, #{order_price}, #{orderer_name}, #{addressnum}, #{address}, #{detailaddress}, #{phone}, #{email}, #{request}, #{request_text}, #{delivery_state}, #{order_date})")
    void insertOrder(OrderBean order);
    
    @Select("SELECT o.*, b.book_name FROM customer_order o JOIN book b ON o.book_id = b.book_id WHERE o.order_number = #{orderNumber}")
    List<OrderBean> getOrderDetails(String orderNumber);
    
    @Select("SELECT co.order_id, co.user_idx, co.order_number, co.order_date, b.book_id, b.book_name, " +
            "co.order_price, co.delivery_state " +
            "FROM customer_order co " +
            "JOIN book b ON co.book_id = b.book_id " +
            "WHERE co.user_idx = #{user_idx} " +
            "ORDER BY co.order_date DESC")
    List<OrderBean> getUserOrderList(int user_idx);
    
    @Select("SELECT co.*, b.book_name, b.book_pic, b.book_price " +
            "FROM customer_order co " +
            "JOIN book b ON co.book_id = b.book_id " +
            "WHERE co.order_number = #{orderNumber} AND co.book_id = #{bookId}")
    List<OrderBean> getOrderDetailByid(@Param("orderNumber") int orderNumber, @Param("bookId") int bookId);
    
    @Update("UPDATE customer_order SET delivery_state = #{cancelReason} WHERE order_number = #{orderNumber} AND book_id = #{bookId}")
    void updateOrderStatus(@Param("orderNumber") String orderNumber, @Param("bookId") int bookId, @Param("cancelReason") String cancelReason);

}