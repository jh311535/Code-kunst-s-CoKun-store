package mapper;

import dto.BoardDTO_admin;
import dto.QnADTO_admin;
import filter.BoardFilterDTO_admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper_admin {

    @Select("SELECT * FROM board WHERE board_id = #{board_id} AND board_info_idx = #{board_info_idx}")
    BoardDTO_admin getBoardById(@Param("board_id") int board_id, @Param("board_info_idx") int board_info_idx);

    @Select("SELECT board_pic FROM board WHERE board_id = #{board_id} AND board_info_idx = #{board_info_idx}")
    String getBoardPicById(@Param("board_id") int board_id, @Param("board_info_idx") int board_info_idx);

    @Select("SELECT * FROM board WHERE board_info_idx = #{board_info_idx}")
    List<BoardDTO_admin> getAllBoards(int board_info_idx);

    @Update({
        "<script>",
        "UPDATE board",
        "SET board_content = #{board_content},",
        "    board_title = #{board_title}",
        "<if test='board_pic != null'>, board_pic = #{board_pic}</if>",
        "WHERE board_id = #{board_id} AND board_info_idx = #{board_info_idx}",
        "</script>"
    })
    void updateBoard(BoardDTO_admin board);

    
    @Update({
        "<script>",
        "UPDATE board",
        "SET board_content = #{board_content},",
        "    board_title = #{board_title}",
        "<if test='board_pic != null'>, board_pic = #{board_pic}</if>",
        "WHERE board_id = #{board_id} AND board_info_idx = #{board_info_idx}",
        "</script>"
    })
    void updateQnABoard(QnADTO_admin qna);

    
    @Update({
        "<script>",
        "UPDATE board",
        "SET board_pic = null ",
        "WHERE board_id = #{board_id} AND board_info_idx = #{board_info_idx}",
        "</script>"
    })
    void deleteBoardPic(BoardDTO_admin board);

    @Update({
        "<script>",
        "UPDATE board",
        "SET board_pic = null ",
        "WHERE board_id = #{board_id} AND board_info_idx = #{board_info_idx}",
        "</script>"
    })
    void deleteQnABoardPic(QnADTO_admin qna);
    
    @Delete("DELETE FROM board WHERE board_id = #{board_id} AND board_info_idx = #{board_info_idx}")
    void deleteBoard(@Param("board_id") int board_id, @Param("board_info_idx") int board_info_idx);

    @Select({
        "<script>",
        "SELECT * FROM (",
        "  SELECT b.*, ROWNUM AS rn FROM (",
        "    SELECT * FROM board b",
        "    WHERE 1=1",
        "    <if test='board_id != null'>AND b.board_id = #{board_id}</if>",
        "    <if test='user_idx != null'>AND b.user_idx = #{user_idx}</if>",
        "    <if test='board_content != null'>AND b.board_content LIKE '%' || #{board_content} || '%'</if>",
        "    <if test='startDate != null'>AND b.board_date &gt;= #{startDate}</if>",
        "    <if test='endDate != null'>AND b.board_date &lt;= #{endDate}</if>",
        "    <if test='minViews != null'>AND b.views &gt;= #{minViews}</if>",
        "    <if test='maxViews != null'>AND b.views &lt;= #{maxViews}</if>",
        "    <if test='board_title != null'>AND b.board_title LIKE '%' || #{board_title} || '%'</if>",
        "    <if test='board_info_idx != null'>AND b.board_info_idx = #{board_info_idx}</if>",
        "    <choose>",
        "      <when test='dateOrder != null'>ORDER BY b.board_date ${dateOrder}</when>",
        "      <when test='boardIdOrder != null'>ORDER BY b.board_id ${boardIdOrder}</when>",
        "      <when test='userIdxOrder != null'>ORDER BY b.user_idx ${userIdxOrder}</when>",
        "      <when test='contentOrder != null'>ORDER BY b.board_content ${contentOrder}</when>",
        "      <when test='viewsOrder != null'>ORDER BY b.views ${viewsOrder}</when>",
        "      <when test='titleOrder != null'>ORDER BY b.board_title ${titleOrder}</when>",
        "      <when test='boardInfoIdxOrder != null'>ORDER BY b.board_info_idx ${boardInfoIdxOrder}</when>",
        "      <when test='picOrder != null'>ORDER BY b.board_pic ${picOrder}</when>",
        "      <otherwise>ORDER BY b.board_id DESC</otherwise>",
        "    </choose>",
        "  ) b",
        "  WHERE ROWNUM &lt;= #{offset} + #{pageSize}",
        ") WHERE rn &gt; #{offset}",
        "</script>"
    })
    List<BoardDTO_admin> getBoardsByFilterAndSort(BoardFilterDTO_admin filter);

    @Select({
        "<script>",
        "SELECT COUNT(*) FROM board b",
        "WHERE 1=1",
        "  <if test='board_id != null'>AND b.board_id = #{board_id}</if>",
        "  <if test='user_idx != null'>AND b.user_idx = #{user_idx}</if>",
        "  <if test='board_content != null'>AND b.board_content LIKE '%' || #{board_content} || '%'</if>",
        "  <if test='startDate != null'>AND b.board_date &gt;= #{startDate}</if>",
        "  <if test='endDate != null'>AND b.board_date &lt;= #{endDate}</if>",
        "  <if test='minViews != null'>AND b.views &gt;= #{minViews}</if>",
        "  <if test='maxViews != null'>AND b.views &lt;= #{maxViews}</if>",
        "  <if test='board_title != null'>AND b.board_title LIKE '%' || #{board_title} || '%'</if>",
        "  <if test='board_info_idx != null'>AND b.board_info_idx = #{board_info_idx}</if>",
        "</script>"
    })
    int getBoardCountByFilter(BoardFilterDTO_admin filter);
    
    // 게시판 제목 가져오기
    @Select("SELECT board_title FROM board WHERE board_id = #{board_id}")
    String getBoardTitleById(int board_id);

    // 게시판 내용 가져오기
    @Select("SELECT board_content FROM board WHERE board_id = #{board_id}")
    String getBoardContentById(int board_id);
    
}
