package kr.co.soldesk.mapper;

import kr.co.soldesk.beans.CommentBean;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

	@Insert("INSERT INTO board_comment (comment_id, board_id, user_idx, comment_content, comment_date) VALUES (comment_seq.nextval, #{board_id}, #{user_idx}, #{comment_content}, SYSDATE)")
	void addComment(CommentBean commentBean);
	
	@Select("SELECT c.comment_id, c.board_id, c.user_idx, c.comment_content, TO_CHAR(c.comment_date, 'YYYY-MM-DD HH24:MI:SS') as comment_date, u.user_nickname FROM board_comment c JOIN user_info u ON c.user_idx = u.user_idx WHERE c.board_id = #{board_id} ORDER BY c.comment_date DESC OFFSET #{startRow} ROWS FETCH NEXT #{commentsPerPage} ROWS ONLY")
    List<CommentBean> getCommentList(@Param("board_id") int board_id, @Param("startRow") int startRow, @Param("commentsPerPage") int commentsPerPage);

    @Select("SELECT COUNT(*) FROM board_comment WHERE board_id = #{board_id}")
    int getCommentCount(int board_id);
    
    @Delete("DELETE FROM board_COMMENT WHERE comment_id = #{comment_id}")
    void deleteComment(int comment_id);
    
    @Select("SELECT a.QnA_idx, a.board_id, a.admin_idx, a.QNA_comment, a.QNA_comment_date, b.admin_name " +
            "FROM QnA_comment a " +
            "LEFT JOIN admin_info b ON a.admin_idx = b.admin_idx " +
            "WHERE a.board_id = #{board_id} AND a.admin_idx IS NOT NULL")
    CommentBean getAdminComment(int board_id);
}
