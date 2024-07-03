package kr.co.soldesk.dao;

import kr.co.soldesk.beans.CommentBean;
import kr.co.soldesk.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAO {

	@Autowired
    private CommentDAO commentDAO;

    public void addComment(CommentBean commentBean) {
        commentDAO.addComment(commentBean);
    }

    public List<CommentBean> getCommentList(int board_id) {
        return commentDAO.getCommentList(board_id);
    }
}