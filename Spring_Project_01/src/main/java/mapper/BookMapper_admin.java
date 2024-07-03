package mapper;

import dto.BookDTO_admin;
import filter.BookFilterDTO_admin;
import filter.UserBookFilterDTO_admin;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper_admin {

    @Select("SELECT * FROM book WHERE book_id = #{book_id}")
    BookDTO_admin getBookById(int book_id);

    @Select("SELECT * FROM book")
    List<BookDTO_admin> getAllBooks();
    
    @Insert({
        "<script>",
        "INSERT INTO book (book_id, book_name, isbn, book_price, publish_date, book_pic, book_info, inventory, publisher, book_category, author)",
        "VALUES (book_seq.NEXTVAL, #{book_name}, #{isbn}, #{book_price}, #{publish_date},",
        "<if test='book_pic != null'>#{book_pic}</if>",
        "<if test='book_pic == null'>NULL</if>,",
        "#{book_info}, #{inventory}, #{publisher}, #{book_category}, #{author})",
        "</script>"
    })
    void insertBook(BookDTO_admin book);

    @Update({
        "<script>",
        "UPDATE book SET ",
        "book_price = #{book_price},",
        "inventory = #{inventory},",
        "<if test='book_pic != null'>book_pic = #{book_pic},</if>",
        "book_info = #{book_info}",
        "WHERE book_id = #{book_id}",
        "</script>"
    })
    void updateBook(BookDTO_admin book);
    

    @Delete("DELETE FROM book WHERE book_id = #{book_id}")
    void deleteBook(int book_id);

    @Select({
        "<script>",
        "SELECT * FROM (",
        "  SELECT b.*, ROWNUM AS rn FROM (",
        "    SELECT * FROM book",
        "    WHERE 1=1",
        "    <if test='book_id != null'>AND book_id = #{book_id}</if>",
        "    <if test='book_name != null'>AND book_name LIKE '%' || #{book_name} || '%'</if>",
        "    <if test='isbn != null'>AND isbn = #{isbn}</if>",
        "    <if test='publisher != null'>AND publisher LIKE '%' || #{publisher} || '%'</if>",
        "    <if test='book_category != null'>AND book_category = #{book_category}</if>",
        "    <if test='author != null'>AND author LIKE '%' || #{author} || '%'</if>",
        "    <if test='startDate != null'>AND publish_date &gt;= TO_DATE(#{startDate}, 'YYYY-MM-DD')</if>",
        "    <if test='endDate != null'>AND publish_date &lt;= TO_DATE(#{endDate}, 'YYYY-MM-DD')</if>",
        "    <if test='minPrice != null'>AND book_price &gt;= #{minPrice}</if>",
        "    <if test='maxPrice != null'>AND book_price &lt;= #{maxPrice}</if>",
        "    <if test='minInventory != null'>AND inventory &gt;= #{minInventory}</if>",
        "    <if test='maxInventory != null'>AND inventory &lt;= #{maxInventory}</if>",
        "    <choose>",
        "      <when test='bookIdOrder != null'>ORDER BY book_id ${bookIdOrder}</when>",
        "      <when test='bookNameOrder != null'>ORDER BY book_name ${bookNameOrder}</when>",
        "      <when test='isbnOrder != null'>ORDER BY isbn ${isbnOrder}</when>",
        "      <when test='priceOrder != null'>ORDER BY book_price ${priceOrder}</when>",
        "      <when test='publishDateOrder != null'>ORDER BY publish_date ${publishDateOrder}</when>",
        "      <when test='publisherOrder != null'>ORDER BY publisher ${publisherOrder}</when>",
        "      <when test='categoryOrder != null'>ORDER BY book_category ${categoryOrder}</when>",
        "      <when test='authorOrder != null'>ORDER BY author ${authorOrder}</when>",
        "      <when test='inventoryOrder != null'>ORDER BY inventory ${inventoryOrder}</when>",
        "      <otherwise>ORDER BY book_id ASC</otherwise>",
        "    </choose>",
        "  ) b",
        ") WHERE rn &gt; #{offset} AND rn &lt;= #{offset} + #{pageSize}",
        "</script>"
    })
    List<BookDTO_admin> getBooksByFilterAndSort(BookFilterDTO_admin filter);

    @Select({
        "<script>",
        "SELECT COUNT(*) FROM book",
        "WHERE 1=1",
        "  <if test='book_id != null'>AND book_id = #{book_id}</if>",
        "  <if test='book_name != null'>AND book_name LIKE '%' || #{book_name} || '%'</if>",
        "  <if test='isbn != null'>AND isbn = #{isbn}</if>",
        "  <if test='publisher != null'>AND publisher LIKE '%' || #{publisher} || '%'</if>",
        "  <if test='book_category != null'>AND book_category = #{book_category}</if>",
        "  <if test='author != null'>AND author LIKE '%' || #{author} || '%'</if>",
        "  <if test='startDate != null'>AND publish_date &gt;= TO_DATE(#{startDate}, 'YYYY-MM-DD')</if>",
        "  <if test='endDate != null'>AND publish_date &lt;= TO_DATE(#{endDate}, 'YYYY-MM-DD')</if>",
        "  <if test='minPrice != null'>AND book_price &gt;= #{minPrice}</if>",
        "  <if test='maxPrice != null'>AND book_price &lt;= #{maxPrice}</if>",
        "  <if test='minInventory != null'>AND inventory &gt;= #{minInventory}</if>",
        "  <if test='maxInventory != null'>AND inventory &lt;= #{maxInventory}</if>",
        "</script>"
    })
    int getBookCountByFilter(BookFilterDTO_admin filter);
    
    // 책 이름을 가져오는 메서드
    @Select("SELECT book_name FROM book WHERE book_id = #{book_id}")
    String getBookNameById(int book_id);
    
    // 책 사진(url)을 가져오는 메서드
    @Select("SELECT book_pic FROM book WHERE book_id = #{book_id}")
    String getBookPicById(int book_id);
    
    
    // 유저의 책 검색 메서드
    @Select({
        "<script>",
        "SELECT * FROM (",
        "  SELECT b.*, ROWNUM AS rn FROM (",
        "    SELECT * FROM book",
        "    WHERE 1=1",
        "    <if test='keyword != null'>",
        "      AND (book_name LIKE '%' || #{keyword} || '%' ",
        "           OR author LIKE '%' || #{keyword} || '%' ",
        "           OR book_category LIKE '%' || #{keyword} || '%' ",
        "           OR publisher LIKE '%' || #{keyword} || '%' ",
        "           OR book_info LIKE '%' || #{keyword} || '%')",
        "    </if>",
        "    <if test='book_name != null'>AND book_name LIKE '%' || #{book_name} || '%'</if>",
        "    <if test='author != null'>AND author LIKE '%' || #{author} || '%'</if>",
        "    <if test='book_category != null'>AND book_category = #{book_category}</if>",
        "    <if test='publisher != null'>AND publisher LIKE '%' || #{publisher} || '%'</if>",
        "    <if test='minPrice != null'>AND book_price &gt;= #{minPrice}</if>",
        "    <if test='maxPrice != null'>AND book_price &lt;= #{maxPrice}</if>",
        "    <choose>",
        "      <when test='sortField != null and sortOrder != null'>ORDER BY ${sortField} ${sortOrder}</when>",
        "      <otherwise>ORDER BY book_id ASC</otherwise>",
        "    </choose>",
        "  ) b",
        ") WHERE rn &gt; #{offset} AND rn &lt;= #{offset} + #{pageSize}",
        "</script>"
    })
    List<BookDTO_admin> searchBooks(UserBookFilterDTO_admin filter);

    // 유저의 책 검색 결과 수
    @Select({
        "<script>",
        "SELECT COUNT(*) FROM book",
        "WHERE 1=1",
        "  <if test='keyword != null'>",
        "    AND (book_name LIKE '%' || #{keyword} || '%' ",
        "         OR author LIKE '%' || #{keyword} || '%' ",
        "         OR book_category LIKE '%' || #{keyword} || '%' ",
        "         OR publisher LIKE '%' || #{keyword} || '%' ",
        "         OR book_info LIKE '%' || #{keyword} || '%')",
        "  </if>",
        "  <if test='book_name != null'>AND book_name LIKE '%' || #{book_name} || '%'</if>",
        "  <if test='author != null'>AND author LIKE '%' || #{author} || '%'</if>",
        "  <if test='book_category != null'>AND book_category = #{book_category}</if>",
        "  <if test='publisher != null'>AND publisher LIKE '%' || #{publisher} || '%'</if>",
        "  <if test='minPrice != null'>AND book_price &gt;= #{minPrice}</if>",
        "  <if test='maxPrice != null'>AND book_price &lt;= #{maxPrice}</if>",
        "</script>"
    })
    int countSearchBooks(UserBookFilterDTO_admin filter);
}
