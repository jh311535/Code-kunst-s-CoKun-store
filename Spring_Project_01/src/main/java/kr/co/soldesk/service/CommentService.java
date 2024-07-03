package kr.co.soldesk.service;

import kr.co.soldesk.beans.CommentBean;
import kr.co.soldesk.beans.PageBean;
import kr.co.soldesk.dao.CommentDAO;
import kr.co.soldesk.mapper.CommentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

	@Autowired
    private CommentMapper commentMapper;

    public void addComment(CommentBean commentBean) {
        commentMapper.addComment(commentBean);
    }
    
    public List<CommentBean> getCommentList(int board_id, int page, int commentsPerPage) {
        int startRow = (page - 1) * commentsPerPage;
        return commentMapper.getCommentList(board_id, startRow, commentsPerPage);
    }

    public PageBean getCommentPageBean(int board_id, int page, int commentsPerPage) {
        int totalComments = commentMapper.getCommentCount(board_id);
        return new PageBean(totalComments, page, commentsPerPage, 3);  // Assuming paginationCnt as 3
    }
    
    public void deleteComment(int comment_id) {
        commentMapper.deleteComment(comment_id);
    }
    
 // CommentService.java
    public CommentBean getAdminComment(int board_id) {
    	System.out.println("Fetching admin comment for board_id: " + board_id);
        return commentMapper.getAdminComment(board_id);
    }
}