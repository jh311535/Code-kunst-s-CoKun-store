package mapper;

import dto.ReviewDTO_admin;
import filter.ReviewFilterDTO_admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper_admin {
    @Select("SELECT * FROM review WHERE review_id = #{review_id}")
    ReviewDTO_admin getReviewById(int review_id);

    @Select("SELECT * FROM review")
    List<ReviewDTO_admin> getAllReviews();

    @Update({
        "<script>",
        "UPDATE review ",
        "SET rating = #{rating}, ",
        "review_content = #{review_content} ",
        "WHERE review_id = #{review_id}",
        "</script>"
    })
    void updateReview(ReviewDTO_admin review);

    @Delete("DELETE FROM review WHERE review_id = #{review_id}")
    void deleteReview(int review_id);

    @Select({
        "<script>",
        "SELECT * FROM (",
        "  SELECT o.*, ROWNUM AS rn FROM (",
        "    SELECT * FROM review",
        "    WHERE 1=1",
        "    <if test='review_id != null'>AND review_id = #{review_id}</if>",
        "    <if test='user_idx != null'>AND user_idx = #{user_idx}</if>",
        "    <if test='book_id != null'>AND book_id = #{book_id}</if>",
        "    <if test='rating_min != null'>AND rating &gt;= #{rating_min}</if>",
        "    <if test='rating_max != null'>AND rating &lt;= #{rating_max}</if>",
        "    <if test='review_content != null'>AND review_content LIKE '%' || #{review_content} || '%'</if>",
        "    <if test='startDate != null'>AND review_date &gt;= TO_DATE(#{startDate}, 'YYYY-MM-DD')</if>",
        "    <if test='endDate != null'>AND review_date &lt;= TO_DATE(#{endDate}, 'YYYY-MM-DD')</if>",
        "    <choose>",
        "      <when test='reviewIdOrder != null'>ORDER BY review_id ${reviewIdOrder}</when>",
        "      <when test='userIdxOrder != null'>ORDER BY user_idx ${userIdxOrder}</when>",
        "      <when test='bookIdOrder != null'>ORDER BY book_id ${bookIdOrder}</when>",
        "      <when test='ratingOrder != null'>ORDER BY rating ${ratingOrder}</when>",
        "      <when test='reviewDateOrder != null'>ORDER BY review_date ${reviewDateOrder}</when>",
        "      <otherwise>ORDER BY review_id ASC</otherwise>",
        "    </choose>",
        "  ) o",
        ") WHERE rn &gt; #{offset} AND rn &lt;= #{offset} + #{pageSize}",
        "</script>"
    })
    List<ReviewDTO_admin> getReviewsByFilterAndSort(ReviewFilterDTO_admin filter);

    @Select({
        "<script>",
        "SELECT COUNT(*) FROM review",
        "WHERE 1=1",
        "  <if test='review_id != null'>AND review_id = #{review_id}</if>",
        "  <if test='user_idx != null'>AND user_idx = #{user_idx}</if>",
        "  <if test='book_id != null'>AND book_id = #{book_id}</if>",
        "  <if test='rating_min != null'>AND rating &gt;= #{rating_min}</if>",
        "  <if test='rating_max != null'>AND rating &lt;= #{rating_max}</if>",
        "  <if test='review_content != null'>AND review_content LIKE '%' || #{review_content} || '%'</if>",
        "  <if test='startDate != null'>AND review_date &gt;= TO_DATE(#{startDate}, 'YYYY-MM-DD')</if>",
        "  <if test='endDate != null'>AND review_date &lt;= TO_DATE(#{endDate}, 'YYYY-MM-DD')</if>",
        "</script>"
    })
    int getReviewCountByFilter(ReviewFilterDTO_admin filter);
}
