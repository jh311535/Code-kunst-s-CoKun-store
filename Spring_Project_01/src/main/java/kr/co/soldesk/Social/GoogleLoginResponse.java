package kr.co.soldesk.Social;

public class GoogleLoginResponse {
    private String access_token;
    private String expires_in;
    private String refreshToken;
    private String scope;
    private String token_type;
    private String id_token;

    public GoogleLoginResponse() {
        // 기본 생성자
    }
    
    
    
    public GoogleLoginResponse(String access_token, String expires_in, String refreshToken, String scope,
			String token_type, String id_token) {
		this.access_token = access_token;
		this.expires_in = expires_in;
		this.refreshToken = refreshToken;
		this.scope = scope;
		this.token_type = token_type;
		this.id_token = id_token;
	}



	public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }
}