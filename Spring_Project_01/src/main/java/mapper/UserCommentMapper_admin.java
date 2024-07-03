package mapper;

import dto.UserCommentDTO_admin;
import filter.UserCommentFilterDTO_admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserCommentMapper_admin {

    @Select("SELECT * FROM board_comment WHERE comment_id = #{comment_id}")
    UserCommentDTO_admin getCommentById(int comment_id);

    @Select("SELECT c.*, b.board_title FROM board_comment c LEFT JOIN board b ON c.board_id = b.board_id WHERE c.comment_id = #{comment_id}")
    UserCommentDTO_admin getCommentWithBoardTitleById(int comment_id);

    @Select({
        "<script>",
        "SELECT * FROM (",
        "  SELECT A.*, ROWNUM AS rn FROM (",
        "    SELECT c.comment_id, c.board_id, c.user_idx, c.comment_content, c.comment_date, b.board_title",
        "    FROM board_comment c",
        "    LEFT JOIN board b ON c.board_id = b.board_id",
        "    WHERE 1=1",
        "      <if test='comment_id != null'>AND c.comment_id = #{comment_id}</if>",
        "      <if test='board_id != null'>AND c.board_id = #{board_id}</if>",
        "      <if test='user_idx != null'>AND c.user_idx = #{user_idx}</if>",
        "      <if test='comment_content != null'>AND c.comment_content LIKE '%' || #{comment_content} || '%'</if>",
        "      <if test='start_date != null'>AND c.comment_date &gt;= TO_DATE(#{start_date}, 'YYYY-MM-DD')</if>",
        "      <if test='end_date != null'>AND c.comment_date &lt;= TO_DATE(#{end_date}, 'YYYY-MM-DD')</if>",
        "      <if test='board_title != null'>AND b.board_title LIKE '%' || #{board_title} || '%'</if>",
        "    ORDER BY ",
        "      <choose>",
        "        <when test='commentIdOrder != null'>c.comment_id ${commentIdOrder}</when>",
        "        <when test='boardIdOrder != null'>c.board_id ${boardIdOrder}</when>",
        "        <when test='userIdxOrder != null'>c.user_idx ${userIdxOrder}</when>",
        "        <when test='contentOrder != null'>c.comment_content ${contentOrder}</when>",
        "        <when test='dateOrder != null'>c.comment_date ${dateOrder}</when>",
        "        <when test='titleOrder != null'>b.board_title ${titleOrder}</when>",
        "        <otherwise>c.comment_id DESC</otherwise>",
        "      </choose>",
        "  ) A",
        "  WHERE ROWNUM &lt;= #{offset} + #{pageSize}",
        ")",
        "WHERE rn &gt; #{offset}",
        "</script>"
    })
    List<UserCommentDTO_admin> getCommentsByFilterAndSort(UserCommentFilterDTO_admin filter);

    @Select({
        "<script>",
        "SELECT COUNT(*) FROM board_comment c",
        "LEFT JOIN board b ON c.board_id = b.board_id",
        "WHERE 1=1",
        "  <if test='comment_id != null'>AND c.comment_id = #{comment_id}</if>",
        "  <if test='board_id != null'>AND c.board_id = #{board_id}</if>",
        "  <if test='user_idx != null'>AND c.user_idx = #{user_idx}</if>",
        "  <if test='comment_content != null'>AND c.comment_content LIKE '%' || #{comment_content} || '%'</if>",
        "  <if test='start_date != null'>AND c.comment_date &gt;= TO_DATE(#{start_date}, 'YYYY-MM-DD')</if>",
        "  <if test='end_date != null'>AND c.comment_date &lt;= TO_DATE(#{end_date}, 'YYYY-MM-DD')</if>",
        "  <if test='board_title != null'>AND b.board_title LIKE '%' || #{board_title} || '%'</if>",
        "</script>"
    })
    int getCommentCountByFilter(UserCommentFilterDTO_admin filter);

    @Update({
        "<script>",
        "UPDATE board_comment",
        "SET comment_content = #{comment_content}",
        "WHERE comment_id = #{comment_id}",
        "</script>"
    })
    void updateComment(UserCommentDTO_admin comment);

    @Delete("DELETE FROM board_comment WHERE comment_id = #{comment_id}")
    void deleteComment(int comment_id);
}
