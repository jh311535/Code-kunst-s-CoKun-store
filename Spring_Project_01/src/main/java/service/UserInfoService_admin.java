package service;

import dao.UserInfoDAO_admin;
import dto.UserInfoDTO_admin;
import filter.UserInfoFilterDTO_admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService_admin {

    @Autowired
    private UserInfoDAO_admin userInfoDAO;

    public UserInfoDTO_admin getUserById(int user_idx) {
        return userInfoDAO.getUserById(user_idx);
    }

    public UserInfoDTO_admin getUserByLoginId(String user_id) {
        return userInfoDAO.getUserByLoginId(user_id);
    }

    public List<UserInfoDTO_admin> getAllUsers() {
        return userInfoDAO.getAllUsers();
    }

    public void updateUser(UserInfoDTO_admin user) {
        userInfoDAO.updateUser(user);
    }

    public void deleteUser(int user_idx) {
        userInfoDAO.deleteUser(user_idx);
    }

    public List<UserInfoDTO_admin> getUsersByFilterAndSort(UserInfoFilterDTO_admin filter) {
        return userInfoDAO.getUsersByFilterAndSort(filter);
    }

    public int getUserCountByFilter(UserInfoFilterDTO_admin filter) {
        return userInfoDAO.getUserCountByFilter(filter);
    }
    

    // 닉네임 중복 체크
    public boolean checkNicknameExists(String user_nickname, Integer user_idx) {
        if (user_idx != null) {
            String existingNickname = userInfoDAO.getUserNicknameByUserIdx(user_idx);
            if (existingNickname.equals(user_nickname)) {
                return false;
            }
        }
    	return userInfoDAO.checkNicknameExists(user_nickname);
    }
    
    // 닉네임을 가져오는 메서드
    public String getUserNicknameByUserIdx(int user_idx) {
    	return userInfoDAO.getUserNicknameByUserIdx(user_idx);
    }
    
    // 유저 사진을 가져오는 메서드
    public String getUserPicByUserIdx(int user_idx) {
    	return userInfoDAO.getUserPicByUserIdx(user_idx);
    }
    
}
