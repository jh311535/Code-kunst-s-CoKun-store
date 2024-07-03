package service;

import dao.UserCommentDAO_admin;
import dto.UserCommentDTO_admin;
import filter.UserCommentFilterDTO_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCommentService_admin {

    @Autowired
    private UserCommentDAO_admin userCommentDAO;

    public UserCommentDTO_admin getCommentById(int comment_id) {
        return userCommentDAO.getCommentById(comment_id);
    }

    public UserCommentDTO_admin getCommentWithBoardTitleById(int comment_id) {
        return userCommentDAO.getCommentWithBoardTitleById(comment_id);
    }

    public List<UserCommentDTO_admin> getCommentsByFilterAndSort(UserCommentFilterDTO_admin filter) {
        return userCommentDAO.getCommentsByFilterAndSort(filter);
    }

    public int getCommentCountByFilter(UserCommentFilterDTO_admin filter) {
        return userCommentDAO.getCommentCountByFilter(filter);
    }

    public void updateComment(UserCommentDTO_admin comment) {
        userCommentDAO.updateComment(comment);
    }

    public void deleteComment(int comment_id) {
        userCommentDAO.deleteComment(comment_id);
    }
}
