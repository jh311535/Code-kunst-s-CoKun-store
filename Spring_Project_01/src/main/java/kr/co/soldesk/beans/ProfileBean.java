package kr.co.soldesk.beans;

import org.springframework.web.multipart.MultipartFile;

public class ProfileBean {
    private int user_idx;
    private String userNickname;
    private int followingCount;
    private int followersCount;
    private int reviewsCount;
    private String introduce;
    private String user_pic; 
    private MultipartFile upload_file;
    private String user_Name;

    // Getters and Setters
    public int getUser_idx() {
        return user_idx;
    }

    public String getUser_Name() {
		return user_Name;
	}

	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}

	public void setUser_idx(int user_idx) {
        this.user_idx = user_idx;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(int reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getUser_pic() {
        return user_pic;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }

    public MultipartFile getUpload_file() {
        return upload_file;
    }

    public void setUpload_file(MultipartFile upload_file) {
        this.upload_file = upload_file;
    }
}