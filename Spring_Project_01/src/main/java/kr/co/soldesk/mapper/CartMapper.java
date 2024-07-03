package kr.co.soldesk.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.soldesk.beans.CartBean;

@Mapper
public interface CartMapper {
    @Insert("INSERT INTO cart (cart_id, user_idx, book_id, cart_quantity) VALUES (cart_seq.nextval, #{user_idx}, #{book_id}, #{cart_quantity})")
    void addToCart(CartBean cart);

    @Select("SELECT c.cart_id, c.user_idx, c.book_id, c.cart_quantity, b.book_pic, b.book_id AS book_id, b.book_name, b.book_price " +
            "FROM cart c " +
            "JOIN book b ON c.book_id = b.book_id " +
            "WHERE c.user_idx = #{user_idx}")
    @Results({
        @Result(property = "cart_id", column = "cart_id"),
        @Result(property = "user_idx", column = "user_idx"),
        @Result(property = "book_id", column = "book_id"),
        @Result(property = "cart_quantity", column = "cart_quantity"),
        @Result(property = "book.book_pic", column = "book_pic"),
        @Result(property = "book.book_id", column = "book_id"),
        @Result(property = "book.book_name", column = "book_name"),
        @Result(property = "book.book_price", column = "book_price")
    })
    List<CartBean> getUserCart(@Param("user_idx") int user_idx);

    @Delete("DELETE FROM cart WHERE cart_id = #{cart_id}")
    void removeFromCart(int cart_id);

    @Update("UPDATE cart SET cart_quantity = #{cart_quantity} WHERE cart_id = #{cart_id}")
    void updateCartQuantity(@Param("cart_id") int cart_id, @Param("cart_quantity") int cart_quantity);
    
    @Delete("delete from cart where cart_id = #{cart_id} and user_idx = #{user_idx}")
	void removeSelected(@Param("cart_id") int cart_id, @Param("user_idx") int user_idx);

    @Select("SELECT * FROM cart WHERE user_idx = #{user_idx}")
    List<CartBean> getCartList(int user_idx);
    
    @Select({
        "<script>",
        "SELECT * FROM cart WHERE cart_id IN",
        "<foreach item='id' collection='cartIds' open='(' separator=',' close=')'>",
        "#{id}",
        "</foreach>",
        "</script>"
    })
    List<CartBean> getCartItemsByIds(@Param("cartIds") List<Integer> cartIds);
    
    @Select("SELECT * FROM cart WHERE user_idx = #{userId}")
    List<CartBean> getCartItemsByUserId(int userId);
    
 // 장바구니에 동일한 책이 있는지 확인
    @Select("SELECT COUNT(*) FROM cart WHERE user_idx = #{user_idx} AND book_id = #{book_id}")
    int checkBookInCart(@Param("user_idx") int user_idx, @Param("book_id") int book_id);
}

