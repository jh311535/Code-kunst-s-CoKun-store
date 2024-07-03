package filter;

public class AdminInfoFilterDTO_admin {
    private Integer admin_idx;
    private String admin_name;
    private String admin_nickname;
    private String login_id;
    private String admin_type;
    private String phone;
    private String email;
    private int offset;
    private int pageSize;
    private String adminIdxOrder;
    private String adminNameOrder;
    private String nickNameOrder;
    private String loginIdOrder;
    private String adminTypeOrder;
    private String phoneOrder;
    private String emailOrder;

    // Getters and Setters
    public Integer getAdmin_idx() {
        return admin_idx;
    }

    public void setAdmin_idx(Integer admin_idx) {
        this.admin_idx = admin_idx;
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

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getAdminIdxOrder() {
        return adminIdxOrder;
    }

    public void setAdminIdxOrder(String adminIdxOrder) {
        this.adminIdxOrder = adminIdxOrder;
    }

    public String getAdminNameOrder() {
        return adminNameOrder;
    }

    public void setAdminNameOrder(String adminNameOrder) {
        this.adminNameOrder = adminNameOrder;
    }

    public String getNickNameOrder() {
        return nickNameOrder;
    }

    public void setNickNameOrder(String nickNameOrder) {
        this.nickNameOrder = nickNameOrder;
    }

    public String getLoginIdOrder() {
        return loginIdOrder;
    }

    public void setLoginIdOrder(String loginIdOrder) {
        this.loginIdOrder = loginIdOrder;
    }

    public String getPhoneOrder() {
        return phoneOrder;
    }

    public void setPhoneOrder(String phoneOrder) {
        this.phoneOrder = phoneOrder;
    }

    public String getEmailOrder() {
        return emailOrder;
    }

    public void setEmailOrder(String emailOrder) {
        this.emailOrder = emailOrder;
    }

	public String getAdmin_type() {
		return admin_type;
	}

	public void setAdmin_type(String admin_type) {
		this.admin_type = admin_type;
	}

	public String getAdminTypeOrder() {
		return adminTypeOrder;
	}

	public void setAdminTypeOrder(String adminTypeOrder) {
		this.adminTypeOrder = adminTypeOrder;
	}
    
}
