package mapper;

import dto.QnADTO_admin;
import filter.QnAFilterDTO_admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QnAMapper_admin {

    @Select("SELECT b.*, qc.qna_comment, qc.qna_comment_date, qc.admin_idx, qc.qna_idx, qc.qna_comment_date " +
            "FROM board b " +
            "LEFT JOIN qna_comment qc ON b.board_id = qc.board_id " +
            "WHERE b.board_id = #{board_id} AND b.board_info_idx = 2")
    QnADTO_admin getQnAById(int board_id);

    @Select({
        "<script>",
        "SELECT * FROM (",
        "  SELECT A.*, ROWNUM AS rn FROM (",
        "    SELECT b.board_id, b.user_idx, b.board_content, b.board_date, b.views, b.board_title, b.board_info_idx, b.board_pic, qc.qna_comment, qc.qna_comment_date",
        "    FROM board b",
        "    LEFT JOIN qna_comment qc ON b.board_id = qc.board_id",
        "    WHERE b.board_info_idx = 2",
        "    <if test='board_id != null'>AND b.board_id = #{board_id}</if>",
        "    <if test='user_idx != null'>AND b.user_idx = #{user_idx}</if>",
        "    <if test='board_content != null'>AND b.board_content LIKE '%' || #{board_content} || '%'</if>",
        "    <if test='startDate != null'>AND b.board_date &gt;= #{startDate}</if>",
        "    <if test='endDate != null'>AND b.board_date &lt;= #{endDate}</if>",
        "    <if test='minViews != null'>AND b.views &gt;= #{minViews}</if>",
        "    <if test='maxViews != null'>AND b.views &lt;= #{maxViews}</if>",
        "    <if test='board_title != null'>AND b.board_title LIKE '%' || #{board_title} || '%'</if>",
        "    <if test='board_info_idx != null'>AND b.board_info_idx = #{board_info_idx}</if>",
        "    <if test='qna_comment != null'>AND qc.qna_comment LIKE '%' || #{qna_comment} || '%'</if>",
        "    <if test='qna_startDate != null'>AND qc.qna_comment_date &gt;= #{qna_startDate}</if>",
        "    <if test='qna_endDate != null'>AND qc.qna_comment_date &lt;= #{qna_endDate}</if>",
        "    ORDER BY ",
        "      <choose>",
        "        <when test='boardIdOrder != null'>b.board_id ${boardIdOrder}</when>",
        "        <when test='userIdxOrder != null'>b.user_idx ${userIdxOrder}</when>",
        "        <when test='contentOrder != null'>b.board_content ${contentOrder}</when>",
        "        <when test='dateOrder != null'>b.board_date ${dateOrder}</when>",
        "        <when test='viewsOrder != null'>b.views ${viewsOrder}</when>",
        "        <when test='titleOrder != null'>b.board_title ${titleOrder}</when>",
        "        <when test='boardInfoIdxOrder != null'>b.board_info_idx ${boardInfoIdxOrder}</when>",
        "        <when test='picOrder != null'>b.board_pic ${picOrder}</when>",
        "        <when test='qnaCommentOrder != null'>qc.qna_comment ${qnaCommentOrder}</when>",
        "        <when test='qnaDateOrder != null'>qc.qna_comment_date ${qnaDateOrder}</when>",
        "        <otherwise>b.board_id DESC</otherwise>",
        "      </choose>",
        "  ) A",
        "  WHERE ROWNUM &lt;= #{offset} + #{pageSize}",
        ")",
        "WHERE rn &gt; #{offset}",
        "</script>"
    })
    List<QnADTO_admin> getQnAsByFilterAndSort(QnAFilterDTO_admin filter);

    @Select({
        "<script>",
        "SELECT COUNT(*) FROM board b",
        "LEFT JOIN qna_comment qc ON b.board_id = qc.board_id",
        "WHERE b.board_info_idx = 2",
        "  <if test='board_id != null'>AND b.board_id = #{board_id}</if>",
        "  <if test='user_idx != null'>AND b.user_idx = #{user_idx}</if>",
        "  <if test='board_content != null'>AND b.board_content LIKE '%' || #{board_content} || '%'</if>",
        "  <if test='startDate != null'>AND b.board_date &gt;= #{startDate}</if>",
        "  <if test='endDate != null'>AND b.board_date &lt;= #{endDate}</if>",
        "  <if test='minViews != null'>AND b.views &gt;= #{minViews}</if>",
        "  <if test='maxViews != null'>AND b.views &lt;= #{maxViews}</if>",
        "  <if test='board_title != null'>AND b.board_title LIKE '%' || #{board_title} || '%'</if>",
        "  <if test='board_info_idx != null'>AND b.board_info_idx = #{board_info_idx}</if>",
        "  <if test='qna_comment != null'>AND qc.qna_comment LIKE '%' || #{qna_comment} || '%'</if>",
        "  <if test='qna_startDate != null'>AND qc.qna_comment_date &gt;= #{qna_startDate}</if>",
        "  <if test='qna_endDate != null'>AND qc.qna_comment_date &lt;= #{qna_endDate}</if>",
        "</script>"
    })
    int getQnACountByFilter(QnAFilterDTO_admin filter);

    @Select("SELECT COUNT(*) FROM qna_comment WHERE board_id = #{board_id}")
    int isAnswered(int board_id);

    @Insert("INSERT INTO qna_comment (qna_idx, board_id, admin_idx, qna_comment, qna_comment_date) VALUES (qna_comment_seq.NEXTVAL, #{board_id}, #{admin_idx}, #{qna_comment}, SYSDATE)")
    void addQnAComment(QnADTO_admin qna);

    @Select("SELECT * FROM qna_comment WHERE board_id = #{board_id}")
    QnADTO_admin getQnACommentByBoardId(int board_id);

    @Update("UPDATE qna_comment SET admin_idx = #{admin_idx}, qna_comment = #{qna_comment} WHERE qna_idx = #{qna_idx}")
    void updateQnAComment(QnADTO_admin qna);

    @Delete("DELETE FROM qna_comment WHERE board_id = #{board_id}")
    void deleteQnAComment(int board_id);
}
