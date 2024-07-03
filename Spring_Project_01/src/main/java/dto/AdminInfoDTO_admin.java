package dto;

import org.springframework.web.multipart.MultipartFile;

public class AdminInfoDTO_admin {
    private int admin_idx;
    private String admin_type;
    private String admin_name;
    private String admin_nickname;
    private String login_id;
    private String login_pwd;
    private String phone;
    private String email;
    private String admin_pic;
    private MultipartFile upload_file;  // 브라우저가 보낸 파일 데이터

    // Getters and Setters
    public int getAdmin_idx() {
        return admin_idx;
    }
    public void setAdmin_idx(int admin_idx) {
        this.admin_idx = admin_idx;
    }
    public String getAdmin_type() {
        return admin_type;
    }
    public void setAdmin_type(String admin_type) {
        this.admin_type = admin_type;
    }
    public String getAdmin_name() {
        return admin_name;
    }
    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }
    public String getAdmin_nickname() {
        return admin_nickname;
    }
    public void setAdmin_nickname(String admin_nickname) {
        this.admin_nickname = admin_nickname;
    }
    public String getLogin_id() {
        return login_id;
    }
    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }
    public String getLogin_pwd() {
        return login_pwd;
    }
    public void setLogin_pwd(String login_pwd) {
        this.login_pwd = login_pwd;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAdmin_pic() {
        return admin_pic;
    }
    public void setAdmin_pic(String admin_pic) {
        this.admin_pic = admin_pic;
    }
    public MultipartFile getUpload_file() {
        return upload_file;
    }
    public void setUpload_file(MultipartFile upload_file) {
        this.upload_file = upload_file;
    }
}
