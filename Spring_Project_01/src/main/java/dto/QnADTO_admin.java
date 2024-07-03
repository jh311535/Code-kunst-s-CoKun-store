package dto;

import org.springframework.web.multipart.MultipartFile;

public class QnADTO_admin {
    // Board 관련 필드
    private int board_id;
    private int user_idx;
    private String board_content;
    private String board_date;
    private int views;
    private String board_title;
    private String board_pic;
    private int board_info_idx;
    private String user_nickname;
    private String formattedDate;
    
    // QnAComment 관련 필드
    private int qna_idx;
    private int admin_idx;
    private String qna_comment;
    private String qna_comment_date;
    private String admin_nickname;
    private boolean hasAnswer;
    private String formattedQnaCommentDate;
    
    private MultipartFile upload_file; // 브라우저가 보낸 파일 데이터

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

    public int getQna_idx() {
        return qna_idx;
    }

    public void setQna_idx(int qna_idx) {
        this.qna_idx = qna_idx;
    }

    public int getAdmin_idx() {
        return admin_idx;
    }

    public void setAdmin_idx(int admin_idx) {
        this.admin_idx = admin_idx;
    }

    public String getQna_comment() {
        return qna_comment;
    }

    public void setQna_comment(String qna_comment) {
        this.qna_comment = qna_comment;
    }

    public String getQna_comment_date() {
        return qna_comment_date;
    }

    public void setQna_comment_date(String qna_comment_date) {
        this.qna_comment_date = qna_comment_date;
    }

    public String getAdmin_nickname() {
        return admin_nickname;
    }

    public void setAdmin_nickname(String admin_nickname) {
        this.admin_nickname = admin_nickname;
    }

	public boolean isHasAnswer() {
		return hasAnswer;
	}

	public void setHasAnswer(boolean hasAnswer) {
		this.hasAnswer = hasAnswer;
	}

	public String getFormattedQnaCommentDate() {
		return formattedQnaCommentDate;
	}

	public void setFormattedQnaCommentDate(String formattedQnaCommentDate) {
		this.formattedQnaCommentDate = formattedQnaCommentDate;
	}

	public MultipartFile getUpload_file() {
		return upload_file;
	}

	public void setUpload_file(MultipartFile upload_file) {
		this.upload_file = upload_file;
	}
    
}
