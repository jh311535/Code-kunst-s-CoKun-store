package kr.co.soldesk.Social;

import java.net.URI;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.service.UserService;

@Controller
@PropertySource("/WEB-INF/properties/application.properties")
public class ApiController {

    @Value("${google.auth.url}")
    private String googleAuthUrl;

    @Value("${google.login.url}")
    private String googleLoginUrl;

    @Value("${google.client.id}")
    private String googleClientId;

    @Value("${google.redirect.url}")
    private String googleRedirectUrl;

    @Value("${google.secret}")
    private String googleClientSecret;

    @Autowired
    private UserService userService;

    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    @GetMapping("/user/getGoogleAuthUrl")
    public ResponseEntity<?> getGoogleAuthUrl(HttpServletRequest request) throws Exception {
        String reqUrl = googleLoginUrl + "/o/oauth2/v2/auth?client_id=" + googleClientId + "&redirect_uri=" + googleRedirectUrl
                + "&response_type=code&scope=email%20profile%20openid&access_type=offline";
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(reqUrl));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/user/socialLogin")
    public String oauth_google_check(HttpServletRequest request,
                                     @RequestParam(value = "code") String authCode,
                                     HttpServletResponse response) throws Exception {
        if (loginUserBean == null) {
            throw new NullPointerException("loginUserBean is null");
        }

        GoogleOAuthRequest googleOAuthRequest = GoogleOAuthRequest.builder()
                .clientId(googleClientId)
                .clientSecret(googleClientSecret)
                .code(authCode)
                .redirectUri(googleRedirectUrl)
                .grantType("authorization_code")
                .build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GoogleLoginResponse> apiResponse = restTemplate.postForEntity(googleAuthUrl + "/token", googleOAuthRequest, GoogleLoginResponse.class);
        GoogleLoginResponse googleLoginResponse = apiResponse.getBody();
        String googleToken = googleLoginResponse.getId_token();
        String requestUrl = UriComponentsBuilder.fromHttpUrl(googleAuthUrl + "/tokeninfo").queryParam("id_token", googleToken).toUriString();
        String resultJson = restTemplate.getForObject(requestUrl, String.class);
        
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(resultJson);
        
        String email = jsonNode.get("email").asText();
        String name = jsonNode.get("name").asText();
        String sub = jsonNode.get("sub").asText();
        
        String userEmail = email;
        String userName = name;
        String userPw = sub;
        
        if (userService.checkGoogleUserIDExist(userEmail)) {
            userService.addGoogleUserInfo(userEmail, userPw, userEmail, userName);
            System.out.println("Google user added: " + userEmail);
        } else {
            System.out.println("Google user already exists: " + userEmail);
            userService.updateGoogleUserInfo(userEmail, userName, userPw); // 사용자가 존재할 때 업데이트 메서드 호출
        }
        
        loginUserBean.setUser_id(userEmail);
        loginUserBean.setUser_pw(userPw);
        loginUserBean.setEmail(userEmail);
        loginUserBean.setUser_name(userName);
        loginUserBean.setUserLogin(true); // 로그인 상태로 변경

        System.out.println("UserBean updated in service");
        
        // 세션 설정
        HttpSession session = request.getSession();
        session.setAttribute("loginUserBean", loginUserBean);
        
        return "/user/login_success";
    }
}