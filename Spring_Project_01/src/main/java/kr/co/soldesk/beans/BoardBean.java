package kr.co.soldesk.beans;


import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

// 게시판 내용 클래스

public class BoardBean {
	@NotNull
	private int board_id;
	@NotNull
	private int user_idx;
	private String board_content;
	private Date board_date;
	private int views;
	private String board_title;
	private int board_like;
	// 첨부파일 이름만 DB에 저장
	private String board_pic;
	// 브라우저가 보낸 파일 데이터
	private MultipartFile upload_file;
	private int board_info_idx;
	private String user_nickname;
	private UserBean userBean;

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

	public Date getBoard_date() {
		return board_date;
	}

	public void setBoard_date(Date board_date) {
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

	public int getBoard_like() {
		return board_like;
	}

	public void setBoard_like(int board_like) {
		this.board_like = board_like;
	}

	public String getBoard_pic() {
		return board_pic;
	}

	public void setBoard_pic(String board_pic) {
		this.board_pic = board_pic;
	}

	public MultipartFile getUpload_file() {
		return upload_file;
	}

	public void setUpload_file(MultipartFile upload_file) {
		this.upload_file = upload_file;
	}

	public int getBoard_info_idx() {
		return board_info_idx;
	}

	public void setBoard_info_idx(int board_info_idx) {
		this.board_info_idx = board_info_idx;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public String getUser_id(){
		return userBean != null ? userBean.getUser_id() : null;
	}


	public void setUser_id(String userId) {
		if (this.userBean != null) {
			this.userBean.setUser_id(userId);
		} else {
			// 필요한 경우, userBean이 null일 때의 처리 로직을 추가할 수 있습니다.
			this.userBean = new UserBean();
			this.userBean.setUser_id(userId);
		}
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	
}
