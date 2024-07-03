package kr.co.soldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import kr.co.soldesk.beans.BookDTO;

public interface BookMapper {

	
	//å
   @Select("SELECT book_id, "
           + "book_name, "
           + "isbn, "
           + "book_price, "
           + "publish_date, "
           + "book_pic, "
           + "book_info, "
           + "inventory, "
           + "publisher, "
           + "book_category, "
           + "author "
           + "FROM book "
           + "ORDER BY book_id")
   List<BookDTO> getBookList(); 
   
   
   //카테고리별모으기
   @Select("<script>"
	        + "SELECT book_id, book_name, isbn, book_price, publish_date, book_pic, book_info, inventory, publisher, book_category, author "
	        + "FROM book "
	        + "WHERE book_category IN "
	        + "<foreach item='category' collection='categories' open='(' separator=',' close=')'>"
	        + "#{category}"
	        + "</foreach> "
	        + "ORDER BY book_id"
	        + "</script>")
	List<BookDTO> getBooksByCategories(@Param("categories") List<String> categories);

   @Select("SELECT book_id, "
           + "book_name, "
           + "isbn, "
           + "book_price, "
           + "publish_date, "
           + "book_pic, "
           + "book_info, "
           + "inventory, "
           + "publisher, "
           + "book_category, "
           + "author "
           + "FROM book "
           + "WHERE book_id = #{book_id}")
   BookDTO getBookById(int book_id);
   
   @Select("SELECT book_price FROM book WHERE book_id = #{book_id}")
   Integer getBookPrice(int book_id);

}
   
