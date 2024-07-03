package mapper;

import dto.CartDTO_admin;
import filter.CartFilterDTO_admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper_admin {
    @Select("SELECT * FROM cart WHERE cart_id = #{cart_id}")
    CartDTO_admin getCartById(int cart_id);

    @Select("SELECT * FROM cart")
    List<CartDTO_admin> getAllCarts();

    @Update({
        "<script>",
        "UPDATE cart ",
        "SET cart_quantity = #{cart_quantity} ",
        "WHERE cart_id = #{cart_id}",
        "</script>"
    })
    void updateCart(CartDTO_admin cart);

    @Delete("DELETE FROM cart WHERE cart_id = #{cart_id}")
    void deleteCart(int cart_id);

    @Select({
        "<script>",
        "SELECT * FROM (",
        "  SELECT o.*, ROWNUM AS rn FROM (",
        "    SELECT * FROM cart",
        "    WHERE 1=1",
        "    <if test='cart_id != null'>AND cart_id = #{cart_id}</if>",
        "    <if test='user_idx != null'>AND user_idx = #{user_idx}</if>",
        "    <if test='book_id != null'>AND book_id = #{book_id}</if>",
        "    <if test='cart_quantity_min != null'>AND cart_quantity &gt;= #{cart_quantity_min}</if>",
        "    <if test='cart_quantity_max != null'>AND cart_quantity &lt;= #{cart_quantity_max}</if>",
        "    <choose>",
        "      <when test='cartIdOrder != null'>ORDER BY cart_id ${cartIdOrder}</when>",
        "      <when test='userIdxOrder != null'>ORDER BY user_idx ${userIdxOrder}</when>",
        "      <when test='bookIdOrder != null'>ORDER BY book_id ${bookIdOrder}</when>",
        "      <when test='cartQuantityOrder != null'>ORDER BY cart_quantity ${cartQuantityOrder}</when>",
        "      <otherwise>ORDER BY cart_id ASC</otherwise>",
        "    </choose>",
        "  ) o",
        ") WHERE rn &gt; #{offset} AND rn &lt;= #{offset} + #{pageSize}",
        "</script>"
    })
    List<CartDTO_admin> getCartsByFilterAndSort(CartFilterDTO_admin filter);

    @Select({
        "<script>",
        "SELECT COUNT(*) FROM cart",
        "WHERE 1=1",
        "  <if test='cart_id != null'>AND cart_id = #{cart_id}</if>",
        "  <if test='user_idx != null'>AND user_idx = #{user_idx}</if>",
        "  <if test='book_id != null'>AND book_id = #{book_id}</if>",
        "  <if test='cart_quantity_min != null'>AND cart_quantity &gt;= #{cart_quantity_min}</if>",
        "  <if test='cart_quantity_max != null'>AND cart_quantity &lt;= #{cart_quantity_max}</if>",
        "</script>"
    })
    int getCartCountByFilter(CartFilterDTO_admin filter);
}
