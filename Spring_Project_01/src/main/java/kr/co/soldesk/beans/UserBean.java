package kr.co.soldesk.beans;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class UserBean {

    private int user_idx;
    public boolean userIdExist;
    public boolean userEmailExist;
    public boolean userNicknameExist;
    

    

	public boolean isUserEmailExist() {
		return userEmailExist;
	}

	public void setUserEmailExist(boolean userEmailExist) {
		this.userEmailExist = userEmailExist;
	}

	public boolean isUserNicknameExist() {
		return userNicknameExist;
	}

	public void setUserNicknameExist(boolean userNicknameExist) {
		this.userNicknameExist = userNicknameExist;
	}
	@NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "[0-9]*")
    private String phone;

    @NotBlank
    @Size(min = 2, max = 4)
    @Pattern(regexp = "[가-힣]*")
    private String user_name;

    @NotBlank
    @Size(min = 6, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String user_id;

    @NotBlank
    @Size(min = 6, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9_]*")
    private String user_nickname;

    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]*")
    private String user_pw;

    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]*")
    private String user_pw2;

    @NotBlank
    private String addressnum;

    @NotBlank
    private String address;

    @NotBlank
    private String detailaddress;

    private boolean userLogin;
    
    

    // Getters and Setters...

    public int getUser_idx() {
        return user_idx;
    }

    public void setUser_idx(int user_idx) {
        this.user_idx = user_idx;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public String getUser_pw2() {
        return user_pw2;
    }

    public void setUser_pw2(String user_pw2) {
        this.user_pw2 = user_pw2;
    }

    public String getAddressnum() {
        return addressnum;
    }

    public void setAddressnum(String addressnum) {
        this.addressnum = addressnum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailaddress() {
        return detailaddress;
    }

    public void setDetailaddress(String detailaddress) {
        this.detailaddress = detailaddress;
    }

    public boolean isUserLogin() {
        return userLogin;
    }

    public void setUserLogin(boolean userLogin) {
        this.userLogin = userLogin;
    }

	public boolean isUserIdExist() {
		return userIdExist;
	}
	public void setUserIdExist(boolean userIdExist) {
		this.userIdExist = userIdExist;
	}

	
}
