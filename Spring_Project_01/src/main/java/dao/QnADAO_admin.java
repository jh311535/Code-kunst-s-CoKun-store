package dao;

import dto.QnADTO_admin;
import filter.QnAFilterDTO_admin;
import mapper.QnAMapper_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QnADAO_admin {

    @Autowired
    private QnAMapper_admin qnaMapper;

    public QnADTO_admin getQnAById(int board_id) {
        return qnaMapper.getQnAById(board_id);
    }

    public List<QnADTO_admin> getQnAsByFilterAndSort(QnAFilterDTO_admin filter) {
        return qnaMapper.getQnAsByFilterAndSort(filter);
    }

    public int getQnACountByFilter(QnAFilterDTO_admin filter) {
        return qnaMapper.getQnACountByFilter(filter);
    }

    public boolean isAnswered(int board_id) {
        return qnaMapper.isAnswered(board_id) > 0;
    }

    public void addQnAComment(QnADTO_admin qna) {
        qnaMapper.addQnAComment(qna);
    }

    public QnADTO_admin getQnACommentByBoardId(int board_id) {
        return qnaMapper.getQnACommentByBoardId(board_id);
    }

    public void updateQnAComment(QnADTO_admin qna) {
        qnaMapper.updateQnAComment(qna);
    }

    public void deleteQnAComment(int board_id) {
        qnaMapper.deleteQnAComment(board_id);
    }
}
