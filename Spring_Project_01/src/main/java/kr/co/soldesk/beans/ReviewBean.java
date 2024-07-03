package kr.co.soldesk.beans;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;


public class ReviewBean {
	private int review_id;
	private int user_idx;
	private int book_id;
	private double rating;
	@NotBlank
	private String review_content;
	private String book_pic;
	private Timestamp review_date;
	private String userNickName;
	private String bookName;
	private String user_pic;
	
	
	public String getUser_pic() {
		return user_pic;
	}
	public void setUser_pic(String user_pic) {
		this.user_pic = user_pic;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public int getReview_id() {
		return review_id;
	}
	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}

	
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public double getRating() {
		return rating;
	}
	
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public String getReview_content() {
		return review_content;
	}
	
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	
	public Timestamp getReview_date() {
		return review_date;
	}
	
	public void setReview_date(Timestamp review_date) {
		this.review_date = review_date;
	}
	public String getBook_pic() {
		return book_pic;
	}
	public void setBook_pic(String book_pic) {
		this.book_pic = book_pic;
	}
	

} 
