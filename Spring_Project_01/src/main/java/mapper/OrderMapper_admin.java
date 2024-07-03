package mapper;

import dto.OrderDTO_admin;
import filter.OrderFilterDTO_admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper_admin {

    @Select("SELECT * FROM customer_order WHERE order_id = #{order_id}")
    OrderDTO_admin getOrderById(int order_id);

    @Select("SELECT * FROM customer_order")
    List<OrderDTO_admin> getAllOrders();

    @Update({
        "<script>",
        "UPDATE customer_order",
        "SET order_quantity = #{order_quantity},",
        "order_price = #{order_price},",
        "request_text = #{request_text},",
        "delivery_state = #{delivery_state}",
        "WHERE order_id = #{order_id}",
        "</script>"
    })
    void updateOrder(OrderDTO_admin order);

    @Delete("DELETE FROM customer_order WHERE order_id = #{order_id}")
    void deleteOrder(int order_id);

    @Select({
        "<script>",
        "SELECT * FROM (",
        "  SELECT o.*, ROWNUM AS rn FROM (",
        "    SELECT * FROM customer_order",
        "    WHERE 1=1",
        "    <if test='order_id != null'>AND order_id = #{order_id}</if>",
        "    <if test='user_idx != null'>AND user_idx = #{user_idx}</if>",
        "    <if test='book_id != null'>AND book_id = #{book_id}</if>",
        "    <if test='order_number != null'>AND order_number = #{order_number}</if>",
        "    <if test='order_quantity_min != null'>AND order_quantity &gt;= #{order_quantity_min}</if>",
        "    <if test='order_quantity_max != null'>AND order_quantity &lt;= #{order_quantity_max}</if>",
        "    <if test='order_price_min != null'>AND order_price &gt;= #{order_price_min}</if>",
        "    <if test='order_price_max != null'>AND order_price &lt;= #{order_price_max}</if>",
        "    <if test='orderer_name != null'>AND orderer_name LIKE '%' || #{orderer_name} || '%'</if>",
        "    <if test='addressnum != null'>AND addressnum LIKE '%' || #{addressnum} || '%'</if>",
        "    <if test='address != null'>AND address LIKE '%' || #{address} || '%'</if>",
        "    <if test='detailaddress != null'>AND detailaddress LIKE '%' || #{detailaddress} || '%'</if>",
        "    <if test='phone != null'>AND phone LIKE '%' || #{phone} || '%'</if>",
        "    <if test='email != null'>AND email LIKE '%' || #{email} || '%'</if>",
        "    <if test='request != null'>AND request LIKE '%' || #{request} || '%'</if>",
        "    <if test='request_text != null'>AND request_text LIKE '%' || #{request_text} || '%'</if>",
        "    <if test='delivery_state != null'>AND delivery_state LIKE '%' || #{delivery_state} || '%'</if>",
        "    <if test='startDate != null'>AND order_date &gt;= TO_DATE(#{startDate}, 'YYYY-MM-DD')</if>",
        "    <if test='endDate != null'>AND order_date &lt;= TO_DATE(#{endDate}, 'YYYY-MM-DD')</if>",
        "    <choose>",
        "      <when test='orderIdOrder != null'>ORDER BY order_id ${orderIdOrder}</when>",
        "      <when test='userIdxOrder != null'>ORDER BY user_idx ${userIdxOrder}</when>",
        "      <when test='bookIdOrder != null'>ORDER BY book_id ${bookIdOrder}</when>",
        "      <when test='orderNumberOrder != null'>ORDER BY order_number ${orderNumberOrder}</when>",
        "      <when test='orderQuantityOrder != null'>ORDER BY order_quantity ${orderQuantityOrder}</when>",
        "      <when test='orderPriceOrder != null'>ORDER BY order_price ${orderPriceOrder}</when>",
        "      <when test='orderDateOrder != null'>ORDER BY order_date ${orderDateOrder}</when>",
        "      <when test='deliveryStateOrder != null'>ORDER BY delivery_state ${deliveryStateOrder}</when>",
        "      <otherwise>ORDER BY order_id ASC</otherwise>",
        "    </choose>",
        "  ) o",
        ") WHERE rn &gt; #{offset} AND rn &lt;= #{offset} + #{pageSize}",
        "</script>"
    })
    List<OrderDTO_admin> getOrdersByFilterAndSort(OrderFilterDTO_admin filter);

    @Select({
        "<script>",
        "SELECT COUNT(*) FROM customer_order",
        "WHERE 1=1",
        "  <if test='order_id != null'>AND order_id = #{order_id}</if>",
        "  <if test='user_idx != null'>AND user_idx = #{user_idx}</if>",
        "  <if test='book_id != null'>AND book_id = #{book_id}</if>",
        "  <if test='order_number != null'>AND order_number = #{order_number}</if>",
        "  <if test='order_quantity_min != null'>AND order_quantity &gt;= #{order_quantity_min}</if>",
        "  <if test='order_quantity_max != null'>AND order_quantity &lt;= #{order_quantity_max}</if>",
        "  <if test='order_price_min != null'>AND order_price &gt;= #{order_price_min}</if>",
        "  <if test='order_price_max != null'>AND order_price &lt;= #{order_price_max}</if>",
        "  <if test='orderer_name != null'>AND orderer_name LIKE '%' || #{orderer_name} || '%'</if>",
        "  <if test='addressnum != null'>AND addressnum LIKE '%' || #{addressnum} || '%'</if>",
        "  <if test='address != null'>AND address LIKE '%' || #{address} || '%'</if>",
        "  <if test='detailaddress != null'>AND detailaddress LIKE '%' || #{detailaddress} || '%'</if>",
        "  <if test='phone != null'>AND phone LIKE '%' || #{phone} || '%'</if>",
        "  <if test='email != null'>AND email LIKE '%' || #{email} || '%'</if>",
        "  <if test='request != null'>AND request LIKE '%' || #{request} || '%'</if>",
        "  <if test='request_text != null'>AND request_text LIKE '%' || #{request_text} || '%'</if>",
        "  <if test='delivery_state != null'>AND delivery_state LIKE '%' || #{delivery_state} || '%'</if>",
        "  <if test='startDate != null'>AND order_date &gt;= TO_DATE(#{startDate}, 'YYYY-MM-DD')</if>",
        "  <if test='endDate != null'>AND order_date &lt;= TO_DATE(#{endDate}, 'YYYY-MM-DD')</if>",
        "</script>"
    })
    int getOrderCountByFilter(OrderFilterDTO_admin filter);
}
