package dao;

import dto.UserInfoDTO_admin;
import filter.UserInfoFilterDTO_admin;
import mapper.UserInfoMapper_admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserInfoDAO_admin {

    @Autowired
    private UserInfoMapper_admin userInfoMapper;

    public UserInfoDTO_admin getUserById(int user_idx) {
        return userInfoMapper.getUserById(user_idx);
    }

    public UserInfoDTO_admin getUserByLoginId(String user_id) {
        return userInfoMapper.getUserByLoginId(user_id);
    }

    public List<UserInfoDTO_admin> getAllUsers() {
        return userInfoMapper.getAllUsers();
    }

    public void updateUser(UserInfoDTO_admin user) {
        userInfoMapper.updateUser(user);
    }

    public void deleteUser(int user_idx) {
        userInfoMapper.deleteUser(user_idx);
    }

    public List<UserInfoDTO_admin> getUsersByFilterAndSort(UserInfoFilterDTO_admin filter) {
        return userInfoMapper.getUsersByFilterAndSort(filter);
    }

    public int getUserCountByFilter(UserInfoFilterDTO_admin filter) {
        return userInfoMapper.getUserCountByFilter(filter);
    }
    

    // 닉네임 중복 체크
    public boolean checkNicknameExists(String user_nickname) {
    	return userInfoMapper.checkNicknameExists(user_nickname) > 0;
    }
    
    // 닉네임을 가져오는 메서드
    public String getUserNicknameByUserIdx(int user_idx) {
    	return userInfoMapper.getUserNicknameByUserIdx(user_idx);
    }
    
    // 유저 사진을 가져오는 메서드
    public String getUserPicByUserIdx(int user_idx) {
    	return userInfoMapper.getUserPicByUserIdx(user_idx);
    }
    
}
