package dto;

import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

public class NoticeDTO_admin {
    private int notice_id;
    private int admin_idx;
    private String notice_title;
    private String notice_content;
    private String notice_date;
    private String notice_pic;
    private int notice_views;
    private MultipartFile upload_file;  // 브라우저가 보낸 파일 데이터
    private String admin_nickname;// admin_info 테이블에서 닉네임이 필요한 경우
    private String formattedDate; // 날짜가 형식에 맞게 바뀐 경우

    // Getters and Setters
    public int getNotice_id() {
        return notice_id;
    }
    public void setNotice_id(int notice_id) {
        this.notice_id = notice_id;
    }
    public int getAdmin_idx() {
        return admin_idx;
    }
    public void setAdmin_idx(int admin_idx) {
        this.admin_idx = admin_idx;
    }
    public String getNotice_title() {
        return notice_title;
    }
    public void setNotice_title(String notice_title) {
        this.notice_title = notice_title;
    }
    public String getNotice_content() {
        return notice_content;
    }
    public void setNotice_content(String notice_content) {
        this.notice_content = notice_content;
    }
    public String getNotice_date() {
        return notice_date;
    }
    public void setNotice_date(String notice_date) {
        this.notice_date = notice_date;
    }
    public String getNotice_pic() {
        return notice_pic;
    }
    public void setNotice_pic(String notice_pic) {
        this.notice_pic = notice_pic;
    }
    public int getNotice_views() {
        return notice_views;
    }
    public void setNotice_views(int notice_views) {
        this.notice_views = notice_views;
    }
    public MultipartFile getUpload_file() {
        return upload_file;
    }
    public void setUpload_file(MultipartFile upload_file) {
        this.upload_file = upload_file;
    }
	public String getAdmin_nickname() {
		return admin_nickname;
	}
	public void setAdmin_nickname(String admin_nickname) {
		this.admin_nickname = admin_nickname;
	}
	public String getFormattedDate() {
		return formattedDate;
	}
	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}
    
}
