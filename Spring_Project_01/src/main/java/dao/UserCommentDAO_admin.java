package dao;

import dto.UserCommentDTO_admin;
import filter.UserCommentFilterDTO_admin;
import mapper.UserCommentMapper_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserCommentDAO_admin {

    @Autowired
    private UserCommentMapper_admin userCommentMapper;

    public UserCommentDTO_admin getCommentById(int comment_id) {
        return userCommentMapper.getCommentById(comment_id);
    }

    public UserCommentDTO_admin getCommentWithBoardTitleById(int comment_id) {
        return userCommentMapper.getCommentWithBoardTitleById(comment_id);
    }

    public List<UserCommentDTO_admin> getCommentsByFilterAndSort(UserCommentFilterDTO_admin filter) {
        return userCommentMapper.getCommentsByFilterAndSort(filter);
    }

    public int getCommentCountByFilter(UserCommentFilterDTO_admin filter) {
        return userCommentMapper.getCommentCountByFilter(filter);
    }

    public void updateComment(UserCommentDTO_admin comment) {
        userCommentMapper.updateComment(comment);
    }

    public void deleteComment(int comment_id) {
        userCommentMapper.deleteComment(comment_id);
    }
}
