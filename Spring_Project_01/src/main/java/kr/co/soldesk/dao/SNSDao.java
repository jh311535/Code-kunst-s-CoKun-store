package kr.co.soldesk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.soldesk.beans.ProfileBean;
import kr.co.soldesk.beans.ReviewBean;
import kr.co.soldesk.beans.SNSBean;
import kr.co.soldesk.mapper.SNSMapper;

@Repository
public class SNSDao {

    @Autowired
    private SNSMapper snsMapper;

    public ProfileBean getSNSprofile(int user_idx) {
        return snsMapper.getSNSprofile(user_idx);
    }

    public void addFollow(SNSBean followSNSBean) {
        snsMapper.addFollow(followSNSBean);
    }

    public void deleteFollow(int follower_id, int following_id) {
        snsMapper.deleteFollow(follower_id, following_id);
    }
    
    public Integer checkFollow(int follwer_id,int follwing_id) {
    	return snsMapper.checkFollow(follwer_id, follwing_id);
    }
    public List<ReviewBean> getUserReview (@Param("user_idx") int user_idx){
    	return snsMapper.getUserReview(user_idx);
    }
    
    public void updateProfile(ProfileBean profileBean) {
    	snsMapper.updateProfile(profileBean);
    }
    
    public void updateNickname(ProfileBean profileBean) {
    	snsMapper.updateNickname(profileBean);
    }
    public List<ProfileBean> getFollowings(int follower_id) {
    	return snsMapper.getFollowings(follower_id);
    }
    public List<ProfileBean> getFollowers(int follower_id) {
    	return snsMapper.getFollowers(follower_id);
    }
}