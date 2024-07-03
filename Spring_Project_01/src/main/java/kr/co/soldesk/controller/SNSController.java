package kr.co.soldesk.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.soldesk.beans.ProfileBean;
import kr.co.soldesk.beans.ReviewBean;
import kr.co.soldesk.beans.SNSBean;
import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.service.SNSService;

@Controller
@RequestMapping("/sns")
public class SNSController {

    @Autowired
    SNSService snsService;

    @GetMapping("/myprofile")
    public String getMyProfile(@RequestParam(value="user_idx", required = false) Integer user_idx,
                                HttpSession session, Model model) {
        UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");

        if (loginUserBean != null && user_idx == null) {
            user_idx = loginUserBean.getUser_idx();
        }

        ProfileBean profile = snsService.getSNSprofile(user_idx);
        List<ReviewBean> userReview = snsService.getUserReview(user_idx);
        Integer checkFollow = null;
        if (loginUserBean != null) {
            checkFollow = snsService.checkFollow(loginUserBean.getUser_idx(), user_idx);
        }
        if(profile.getUser_pic()==null) {
        	profile.setUser_pic("default_profile.png");
        }
        //팔로워 리스트 추가
    	List<ProfileBean> getFollow = snsService.getFollowings(user_idx);
    	model.addAttribute("getFollow",getFollow);
        
        //팔로우리스트 추가
    	List<ProfileBean> getFollower = snsService.getFollowers(user_idx);
    	model.addAttribute("getFollower",getFollower);
    	
        model.addAttribute("loginUserBean", loginUserBean);
        model.addAttribute("profile", profile);
        model.addAttribute("requestUserIdx", user_idx);
        model.addAttribute("checkFollow", checkFollow);
        model.addAttribute("userReview", userReview);

        return "sns/myprofile";
    }
    
    @GetMapping("/profile_modify")
    public String profileModify(@RequestParam(value="user_idx", required = false) Integer user_idx,
                                HttpSession session,
                                Model model) {
        UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");
        if (loginUserBean.getUser_idx() != user_idx) {
            return "user/not_login";
        }
        ProfileBean profile = snsService.getSNSprofile(loginUserBean.getUser_idx());
        if (profile.getUser_pic() == null) {
            profile.setUser_pic("default_profile.png");
        }
        
        model.addAttribute("profile", profile);
        
        return "sns/profile_modify";
    }

    @PostMapping("/profile_modify_pro")
    public String profileModifyPro(@ModelAttribute("profile") ProfileBean profileBean,
                                   HttpSession session, Model model) {
        UserBean loginUserBean = (UserBean)session.getAttribute("loginUserBean");

        // 업로드된 파일을 처리
        MultipartFile uploadFile = profileBean.getUpload_file();
        if (uploadFile != null && !uploadFile.isEmpty()) {
            String fileName = snsService.saveUploadFile(uploadFile);
            profileBean.setUser_pic(fileName);
        }
        
        profileBean.setUser_idx(loginUserBean.getUser_idx()); // user_idx 설정

        snsService.updateProfile(profileBean);
        snsService.updateNickname(profileBean);

        // 업데이트된 프로필 정보를 다시 가져옵니다
        ProfileBean updatedProfile = snsService.getSNSprofile(loginUserBean.getUser_idx());
        model.addAttribute("profile", updatedProfile);

        return "sns/profile_modify_success";
    }
   
    

    @PostMapping("/addFollow")
    @ResponseBody
    public ProfileBean addFollow(@RequestBody SNSBean followSNSBean) {
        try {
            snsService.addFollow(followSNSBean);
            return snsService.getSNSprofile(followSNSBean.getFollowing_id());
        } catch (Exception e) {
            return new ProfileBean();
        }
    }

    @PostMapping("/deleteFollow")
    @ResponseBody
    public ProfileBean deleteFollow(@RequestBody SNSBean followSNSBean) {
        try {
            snsService.deleteFollow(followSNSBean.getFollower_id(), followSNSBean.getFollowing_id());
            return snsService.getSNSprofile(followSNSBean.getFollowing_id());
        } catch (Exception e) {
            return new ProfileBean();
        }
    }

    @GetMapping("/checkFollow")
    @ResponseBody
    public Integer checkFollow(@RequestParam("follower_id") int follower_id, @RequestParam("following_id") int following_id) {
        return snsService.checkFollow(follower_id, following_id);
    }
    
}