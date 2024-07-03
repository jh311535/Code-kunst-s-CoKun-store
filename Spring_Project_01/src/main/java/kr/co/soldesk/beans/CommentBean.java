package kr.co.soldesk.beans;

import java.util.Date;

public class CommentBean {
    private int comment_id;
    private int board_id;
    private int user_idx;
    private String comment_content;
    private String comment_date;
    private String user_nickname;
    private int QnA_idx;
    private int admin_idx;
    private String QNA_comment;
    private Date QNA_comment_date;
    private String admin_name;
    
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
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
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public String getComment_date() {
		return comment_date;
	}
	public void setComment_date(String comment_date) {
		this.comment_date = comment_date;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public int getQnA_idx() {
		return QnA_idx;
	}
	public void setQnA_idx(int qnA_idx) {
		QnA_idx = qnA_idx;
	}
	public int getAdmin_idx() {
		return admin_idx;
	}
	public void setAdmin_idx(int admin_idx) {
		this.admin_idx = admin_idx;
	}
	public String getQNA_comment() {
		return QNA_comment;
	}
	public void setQNA_comment(String qNA_comment) {
		QNA_comment = qNA_comment;
	}
	public Date getQNA_comment_date() {
		return QNA_comment_date;
	}
	public void setQNA_comment_date(Date qNA_comment_date) {
		QNA_comment_date = qNA_comment_date;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	
    

}