package dao;

import dto.BoardDTO_admin;
import dto.QnADTO_admin;
import filter.BoardFilterDTO_admin;
import mapper.BoardMapper_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAO_admin {

    @Autowired
    private BoardMapper_admin boardMapper;

    public BoardDTO_admin getBoardById(int board_id, int board_info_idx) {
        return boardMapper.getBoardById(board_id, board_info_idx);
    }

    public String getBoardPicById(int board_id, int board_info_idx) {
        return boardMapper.getBoardPicById(board_id, board_info_idx);
    }

    public List<BoardDTO_admin> getAllBoards(int board_info_idx) {
        return boardMapper.getAllBoards(board_info_idx);
    }

    public void updateBoard(BoardDTO_admin board) {
        boardMapper.updateBoard(board);
    }
    
    public void updateQnABoard(QnADTO_admin qna) {
        boardMapper.updateQnABoard(qna);
    }

    public void deleteBoardPic(BoardDTO_admin board) {
        boardMapper.deleteBoardPic(board);
    }

    public void deleteQnABoardPic(QnADTO_admin qna) {
        boardMapper.deleteQnABoardPic(qna);
    }
    
    public void deleteBoard(int board_id, int board_info_idx) {
        boardMapper.deleteBoard(board_id, board_info_idx);
    }

    public List<BoardDTO_admin> getBoardsByFilterAndSort(BoardFilterDTO_admin filter) {
        return boardMapper.getBoardsByFilterAndSort(filter);
    }

    public int getBoardCountByFilter(BoardFilterDTO_admin filter) {
        return boardMapper.getBoardCountByFilter(filter);
    }
    

    // 게시판 제목 가져오기
    public String getBoardTitleById(int board_id) {
        return boardMapper.getBoardTitleById(board_id);
    }
    
    // 게시판 내용 가져오기
    public String getBoardContentById(int board_id) {
        return boardMapper.getBoardContentById(board_id);
    }
    
}
