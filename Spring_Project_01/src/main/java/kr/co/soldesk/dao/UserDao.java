package kr.co.soldesk.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.mapper.UserMapper;

@Repository
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    public String checkUserIdExist(String user_id) {
        return userMapper.checkUserIdExist(user_id);
    }

    public String checkUserEmailExist(String email) {
        return userMapper.checkUserEmailExist(email);
    }

    public String checkUserNicknameExist(String user_nickname) {
        return userMapper.checkUserNicknameExist(user_nickname);
    }

    public void addUserInfo(UserBean joinUserBean) {
        userMapper.addUserInfo(joinUserBean);
    }

    public String findUserId(String user_name, String email, String phone) {
        return userMapper.findUserId(user_name, email, phone);
    }

    public String findUserPassword(String user_id, String user_name, String email) {
        return userMapper.findUserPassword(user_id, user_name, email);
    }

    public UserBean getUserInfo(int user_idx) {
        return userMapper.getUserInfo(user_idx);
    }
    
    public UserBean getLoginUserInfo(String user_id) {
    	return userMapper.getLoginUserInfo(user_id);
    }

    public void modifyUserInfo(UserBean modifyUserBean) {
        userMapper.modifyUserInfo(modifyUserBean);
    }

    public boolean checkPassword(String user_id, String user_pw) {
        return userMapper.checkPassword(user_id, user_pw);
    }
    
    public void deleteUser(int user_idx) {
        userMapper.deleteUser(user_idx);
    }
    
    public UserBean getUserByIdx(int user_idx){
		return userMapper.getUserByIdx(user_idx);
	}
    
    public void addGoogleUserInfo(String user_id, String user_pw, String user_email, String user_name) {
		userMapper.addGoogleUserInfo(user_id, user_pw, user_email, user_name);
	}
	
	public UserBean getGoogleLoginUserInfo(String user_id) {
		return userMapper.getGoogleLoginUserInfo(user_id);
	}
	
	public String checkGoogleUserIDExist(String user_id) {
		return userMapper.checkGoogleUserIDExist(user_id);
	}

}

