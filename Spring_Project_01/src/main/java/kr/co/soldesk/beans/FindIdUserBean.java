package kr.co.soldesk.beans;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class FindIdUserBean {
	
	
	//�븘�씠�뵒 李얘린
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
    
    
	
}
