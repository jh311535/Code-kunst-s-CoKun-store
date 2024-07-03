package kr.co.soldesk.Social;

public class GoogleOAuthRequest {
    private String redirectUri;
    private String clientId;
    private String clientSecret;
    private String code;
    private String responseType;
    private String scope;
    private String accessType;
    private String grantType;
    private String state;
    private String includeGrantedScopes;
    private String loginHint;
    private String prompt;
    
	public String getRedirectUri() {
		return redirectUri;
	}
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getResponseType() {
		return responseType;
	}
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIncludeGrantedScopes() {
		return includeGrantedScopes;
	}
	public void setIncludeGrantedScopes(String includeGrantedScopes) {
		this.includeGrantedScopes = includeGrantedScopes;
	}
	public String getLoginHint() {
		return loginHint;
	}
	public void setLoginHint(String loginHint) {
		this.loginHint = loginHint;
	}
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	public GoogleOAuthRequest(String redirectUri, String clientId, String clientSecret, String code,
			String responseType, String scope, String accessType, String grantType, String state,
			String includeGrantedScopes, String loginHint, String prompt, String name) {
		super();
		this.redirectUri = redirectUri;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.code = code;
		this.responseType = responseType;
		this.scope = scope;
		this.accessType = accessType;
		this.grantType = grantType;
		this.state = state;
		this.includeGrantedScopes = includeGrantedScopes;
		this.loginHint = loginHint;
		this.prompt = prompt;
	}
	
	public GoogleOAuthRequest() {
		// TODO Auto-generated constructor stub
	}
	
    public static GoogleOAuthRequestBuilder builder() {
        return new GoogleOAuthRequestBuilder();
    }

    public static class GoogleOAuthRequestBuilder {
        private GoogleOAuthRequest instance = new GoogleOAuthRequest();

        public GoogleOAuthRequestBuilder redirectUri(String redirectUri) {
            instance.redirectUri = redirectUri;
            return this;
        }

        public GoogleOAuthRequestBuilder clientId(String clientId) {
            instance.clientId = clientId;
            return this;
        }
        public GoogleOAuthRequestBuilder clientSecret(String clientSecret) {
            instance.clientSecret = clientSecret;
            return this;
        }

        public GoogleOAuthRequestBuilder code(String code) {
            instance.code = code;
            return this;
        }

        public GoogleOAuthRequestBuilder responseType(String responseType) {
            instance.responseType = responseType;
            return this;
        }

        public GoogleOAuthRequestBuilder scope(String scope) {
            instance.scope = scope;
            return this;
        }

        public GoogleOAuthRequestBuilder accessType(String accessType) {
            instance.accessType = accessType;
            return this;
        }

        public GoogleOAuthRequestBuilder grantType(String grantType) {
            instance.grantType = grantType;
            return this;
        }

        public GoogleOAuthRequestBuilder state(String state) {
            instance.state = state;
            return this;
        }

        public GoogleOAuthRequestBuilder includeGrantedScopes(String includeGrantedScopes) {
            instance.includeGrantedScopes = includeGrantedScopes;
            return this;
        }

        public GoogleOAuthRequestBuilder loginHint(String loginHint) {
            instance.loginHint = loginHint;
            return this;
        }

        public GoogleOAuthRequestBuilder prompt(String prompt) {
            instance.prompt = prompt;
            return this;
        }

        public GoogleOAuthRequest build() {
            return instance;
        }
    }
    
}