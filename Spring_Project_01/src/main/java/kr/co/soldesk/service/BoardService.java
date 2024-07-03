package kr.co.soldesk.service;

import kr.co.soldesk.beans.BoardBean;
import kr.co.soldesk.beans.PageBean;
import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.dao.BoardDao;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.swing.text.AbstractDocument;
import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {

    @Value("${path.upload}")
    private String path_upload;

    @Value("${page.listcnt}")
    private int page_listcnt;

    @Value("${page.paginationcnt}")
    private int page_paginationcnt;

    @Autowired
    private BoardDao boardDao;

    // 로그인 되었는지 확인하고 써야함
    @Resource(name="loginUserBean")
    private UserBean loginUserBean;

    @SuppressWarnings("unused") // 파일 없을 때 쓰는 경고문 뜨지 않게
    private String saveUploadFile(MultipartFile upload_file){

        // 원래 파일 이름을 UTF-8로 인코딩
        String originalFileName = upload_file.getOriginalFilename();
        String encodedFileName = "";
        try {
            encodedFileName = URLEncoder.encode(originalFileName, StandardCharsets.UTF_8.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 파일 이름을 현재 시간으로 변경하여 저장
        String file_name = System.currentTimeMillis() + "_" + encodedFileName;

        try {// 경로와 파일 이름
            upload_file.transferTo(new File(path_upload + "/" + file_name)); //
        }catch(Exception e) {
            e.printStackTrace();
        }

        return file_name;
    }//saveUploadFile

    public void addBoardInfo(BoardBean writeBoardBean){
        System.out.println(writeBoardBean.getBoard_title());
        System.out.println(writeBoardBean.getBoard_content());
        System.out.println(writeBoardBean.getUpload_file().getSize());


        MultipartFile upload_file = writeBoardBean.getUpload_file();

        if(upload_file.getSize() > 0) { // 이미지 파일 불러왔는지 확인
            String file_name = saveUploadFile(upload_file);
            System.out.println(file_name);
            writeBoardBean.setBoard_pic(file_name); // 오라클에 파일 이름 저장
        }

        boardDao.addBoardInfo(writeBoardBean);

    }

    public String getBoardInfoName(int board_info_idx) {
        return boardDao.getBoardInfoName(board_info_idx);
    }

    public List<BoardBean> getBoardList(int board_info_idx, int page) {


        int start = (page-1)*page_listcnt;
        RowBounds rowBounds = new RowBounds(start, 10);

        return boardDao.getBoardList(board_info_idx, rowBounds);
    } 

    public void updateHits(int board_id) {
        boardDao.updateHits(board_id);
    }


    public BoardBean getBoardInfo(int board_id){
        return boardDao.getBoardInfo(board_id);
    }

    public void modifyBoardInfo(BoardBean modifyBoardBean) {

        MultipartFile upload_file=modifyBoardBean.getUpload_file(); // board_file과는 다름. 그림이 없는데 그림을 긁으면 에러가 나서 예외처리해야 함

        if(upload_file.getSize() > 0) {
            String file_name = saveUploadFile(upload_file);
            System.out.println(file_name);
            modifyBoardBean.setBoard_pic(file_name);//오라클에 파일 이름 저장
        }

        modifyBoardBean.setUser_idx(loginUserBean.getUser_idx());
        modifyBoardBean.setUser_id(loginUserBean.getUser_id());
        boardDao.modifyBoardInfo(modifyBoardBean);
    }


    public void deleteBoardInfo(int board_id) {
        boardDao.deleteBoardInfo(board_id);
    }

    public PageBean getBoardCnt(int board_info_idx, int currentPage) {

        int board_cnt = boardDao.getBoardCnt(board_info_idx);// 전체 게시물 수 반납
        PageBean pageBean = new PageBean(board_cnt, currentPage, page_listcnt, page_paginationcnt);

        return pageBean;
    }

}
