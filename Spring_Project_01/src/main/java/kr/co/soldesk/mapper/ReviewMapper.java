package kr.co.soldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import kr.co.soldesk.beans.ReviewBean;

@Mapper
public interface ReviewMapper {
	
	
	//리뷰쓰기
    @Insert("INSERT INTO review (review_id, user_idx, book_id, rating, review_content, review_date) " +
            "VALUES (REVIEW_SEQ.NEXTVAL, #{user_idx}, #{book_id}, #{rating}, #{review_content}, #{review_date})")
    void addReview(ReviewBean review);

        @Select("SELECT r.review_id, r.user_idx, r.book_id, r.rating, r.review_content, "
        		+ "r.review_date,b.book_pic AS book_pic, u.user_nickname AS userNickName " +
                "FROM review r " +
                "JOIN book b ON r.book_id = b.book_id " +
                "JOIN user_info u ON r.user_idx = u.user_idx " +
                "WHERE r.book_id = #{book_id}"+
                "ORDER BY r.review_date DESC")
        List<ReviewBean> getReview(int book_id, RowBounds rowBounds);

    @Select("SELECT COUNT(*) FROM review WHERE book_id = #{book_id}")
    int countReviewsForBook(int bookId);
      
    @Delete("DELETE FROM review WHERE review_id = #{review_id}")
    void deleteReview(@Param("review_id") int review_id);
    
    @Update("update review set review_content=#{review_content}, rating=#{rating} where review_id=#{review_id}")
    void modifyReview(ReviewBean reviewBean); 
    
    //리뷰 불러오기
    @Select("SELECT r.review_id, r.user_idx, r.book_id, r.rating, r.review_content, "
    		+ "r.review_date, u.user_nickname AS userNickName, b.book_name AS bookName " +
            "FROM review r " +
            "JOIN user_info u ON r.user_idx = u.user_idx " +
            "JOIN book b ON r.book_id = b.book_id " +
            "WHERE r.review_id = #{review_id}"+
            "ORDER BY r.review_date DESC")
    ReviewBean getReviewContent(int review_id);    

    @Select("SELECT r.review_id, r.user_idx, r.book_id, r.rating, r.review_content, r.review_date, "
            + "b.book_name AS bookName, b.book_pic AS book_pic, "
            + "u.user_nickname AS userNickName, c.user_pic AS user_pic "
            + "FROM review r "
            + "JOIN book b ON r.book_id = b.book_id "
            + "JOIN user_info u ON r.user_idx = u.user_idx "
            + "JOIN customer c ON r.user_idx = c.user_idx "
            + "WHERE r.user_idx IN (SELECT following_id FROM follow WHERE follower_id = #{user_idx})"
            + "ORDER BY r.review_date DESC")
    List<ReviewBean> getFollowReview(int user_idx, RowBounds rowBounds);
    
    @Select("SELECT * FROM ("
    		+"SELECT r.review_id, r.user_idx, r.book_id, r.rating, r.review_content, "
    		+ "b.book_name AS bookName,r.review_date,b.book_pic AS book_pic,"
    		+ "u.user_nickname AS userNickName , c.user_pic AS user_pic " +
    		"FROM review r " +
    		"JOIN book b ON r.book_id = b.book_id " +
    		"JOIN user_info u ON r.user_idx = u.user_idx " +
            "JOIN customer c ON r.user_idx=c.user_idx " +
    		"ORDER BY r.review_date DESC "
    	    + ") WHERE ROWNUM <= 6")
    List<ReviewBean> getAllReview(RowBounds rowBounds);
}