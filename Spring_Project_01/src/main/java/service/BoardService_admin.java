package service;

import dao.BoardDAO_admin;
import dto.BoardDTO_admin;
import dto.QnADTO_admin;
import filter.BoardFilterDTO_admin;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@PropertySource("/WEB-INF/properties/admin_option.properties")
public class BoardService_admin {

    @Autowired
    private BoardDAO_admin boardDAO;

    @Value("${path.upload}")
    private String path_upload;

    public BoardDTO_admin getBoardById(int board_id, int board_info_idx) {
        return boardDAO.getBoardById(board_id, board_info_idx);
    }

    public List<BoardDTO_admin> getAllBoards(int board_info_idx) {
        return boardDAO.getAllBoards(board_info_idx);
    }

    public void updateBoard(BoardDTO_admin board, boolean deletePic) {
        if (deletePic) {
            deleteExistingPic(board.getBoard_pic());
            boardDAO.deleteBoardPic(board);
        } else {
            MultipartFile upload_file = board.getUpload_file();
            if (upload_file != null && !upload_file.isEmpty()) {
                String file_name = saveUploadFile(upload_file);
                board.setBoard_pic(file_name);
            }
        }
        boardDAO.updateBoard(board);
    }
    
    public void updateQnABoard(QnADTO_admin qna, boolean deletePic) {
        if (deletePic) {
            deleteExistingPic(qna.getBoard_pic());
            boardDAO.deleteQnABoardPic(qna);
        } else {
            MultipartFile upload_file = qna.getUpload_file();
            if (upload_file != null && !upload_file.isEmpty()) {
                String file_name = saveUploadFile(upload_file);
                qna.setBoard_pic(file_name);
            }
        }
        boardDAO.updateQnABoard(qna);
    }


    public void deleteBoard(int board_id, int board_info_idx) {
        String fileName = boardDAO.getBoardPicById(board_id, board_info_idx);
        if (fileName != null) {
            deleteExistingPic(fileName);
        }
        boardDAO.deleteBoard(board_id, board_info_idx);
    }
    
    public List<BoardDTO_admin> getBoardsByFilterAndSort(BoardFilterDTO_admin filter) {
        return boardDAO.getBoardsByFilterAndSort(filter);
    }

    public int getBoardCountByFilter(BoardFilterDTO_admin filter) {
        return boardDAO.getBoardCountByFilter(filter);
    }

    private String saveUploadFile(MultipartFile upload_file) {
        String file_name = System.currentTimeMillis() + "_"
                + FilenameUtils.getBaseName(upload_file.getOriginalFilename()) + "."
                + FilenameUtils.getExtension(upload_file.getOriginalFilename());

        try {
            upload_file.transferTo(new File(path_upload + "/" + file_name));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file_name;
    }

    private void deleteExistingPic(String fileName) {
        if (fileName != null && !fileName.isEmpty()) {
            File file = new File(path_upload + "/" + fileName);
            if (file.exists()) {
                file.delete();
            }
        }
    }
    

    // 게시판 제목 가져오기
    public String getBoardTitleById(int board_id) {
        return boardDAO.getBoardTitleById(board_id);
    }
    
    
    // 게시판 내용 가져오기
    public String getBoardContentById(int board_id) {
        return boardDAO.getBoardContentById(board_id);
    }
    
}
