package service;

import dao.QnADAO_admin;
import dto.QnADTO_admin;
import filter.QnAFilterDTO_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnAService_admin {

    @Autowired
    private QnADAO_admin qnaDAO;

    public QnADTO_admin getQnAById(int board_id) {
        return qnaDAO.getQnAById(board_id);
    }

    public List<QnADTO_admin> getQnAsByFilterAndSort(QnAFilterDTO_admin filter) {
        return qnaDAO.getQnAsByFilterAndSort(filter);
    }

    public int getQnACountByFilter(QnAFilterDTO_admin filter) {
        return qnaDAO.getQnACountByFilter(filter);
    }

    public boolean isAnswered(int board_id) {
        return qnaDAO.isAnswered(board_id);
    }

    public void addQnAComment(QnADTO_admin qna) {
        qnaDAO.addQnAComment(qna);
    }

    public QnADTO_admin getQnACommentByBoardId(int board_id) {
        return qnaDAO.getQnACommentByBoardId(board_id);
    }

    public void updateQnAComment(QnADTO_admin qna) {
        qnaDAO.updateQnAComment(qna);
    }

    public void deleteQnAComment(int board_id) {
        qnaDAO.deleteQnAComment(board_id);
    }
}
