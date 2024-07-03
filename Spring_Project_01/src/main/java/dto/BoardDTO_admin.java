package dto;

import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

public class BoardDTO_admin {
    private int board_id;
    private int user_idx;
    private String board_content;
    private String board_date;
    private int views;
    private String board_title;
    private String board_pic;
    private int board_info_idx;
    private MultipartFile upload_file; // 브라우저가 보낸 파일 데이터
    private String user_nickname; // user_info 테이블에서 닉네임이 필요한 경우
    private String formattedDate; // 날짜가 형식에 맞게 바뀐 경우

    // Getters and Setters
    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public int getUser_idx() {
        return user_idx;
    }

    public void setUser_idx(int user_idx) {
        this.user_idx = user_idx;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public String getBoard_date() {
        return board_date;
    }

    public void setBoard_date(String board_date) {
        this.board_date = board_date;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getBoard_title() {
        return board_title;
    }

    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }

    public String getBoard_pic() {
        return board_pic;
    }

    public void setBoard_pic(String board_pic) {
        this.board_pic = board_pic;
    }

    public int getBoard_info_idx() {
        return board_info_idx;
    }

    public void setBoard_info_idx(int board_info_idx) {
        this.board_info_idx = board_info_idx;
    }

    public MultipartFile getUpload_file() {
        return upload_file;
    }

    public void setUpload_file(MultipartFile upload_file) {
        this.upload_file = upload_file;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }
}
