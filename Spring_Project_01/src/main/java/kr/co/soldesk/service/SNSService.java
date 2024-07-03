package kr.co.soldesk.service;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.soldesk.beans.ProfileBean;
import kr.co.soldesk.beans.ReviewBean;
import kr.co.soldesk.beans.SNSBean;
import kr.co.soldesk.dao.SNSDao;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class SNSService {

	@Value("${path.upload}")
	   private String path_upload;
	
    @Autowired
    private SNSDao snsDao;
    
    public String saveUploadFile(MultipartFile uploadFile) {
        String originalFileName = uploadFile.getOriginalFilename();
        String fileName = System.currentTimeMillis() + "_" + originalFileName;
        String filePath = path_upload + "/" + fileName;

        try {
            File file = new File(filePath);
            uploadFile.transferTo(file);
            System.out.println("File saved to: " + filePath); // 디버그 로그 추가
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileName;
    }
    
    

    public ProfileBean getSNSprofile(int user_idx) {
        return snsDao.getSNSprofile(user_idx);
    }
    
    public void addFollow(SNSBean followSNSBean) {
        snsDao.addFollow(followSNSBean);
    }

    public void deleteFollow(int follower_id, int following_id) {
        snsDao.deleteFollow(follower_id, following_id);
    }
    
    public Integer checkFollow(int follwer_id,int follwing_id) {
    	return snsDao.checkFollow(follwer_id, follwing_id);
    }
    
    public List<ReviewBean> getUserReview (@Param("user_idx") int user_idx){
    	return snsDao.getUserReview(user_idx);
    }
    
    public void updateProfile(ProfileBean profileBean) {
        snsDao.updateProfile(profileBean);
    }
    
    public void updateNickname(ProfileBean profileBean) {
    	snsDao.updateNickname(profileBean);
    }
    
    public List<ProfileBean> getFollowings(int follower_id) {
    	return snsDao.getFollowings(follower_id);
    }
    public List<ProfileBean> getFollowers(int follower_id) {
    	return snsDao.getFollowers(follower_id);
    }
}