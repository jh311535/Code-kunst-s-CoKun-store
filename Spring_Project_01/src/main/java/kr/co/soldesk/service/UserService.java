package kr.co.soldesk.service;



import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.dao.UserDao;
import kr.co.soldesk.mapper.UserMapper;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    
    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;
    
    @Autowired
    private UserMapper userMapper;

    // 아이디 중복 검사
    public boolean checkUserIdExist(String user_id) {
        String user_name = userDao.checkUserIdExist(user_id);
        return user_name == null;
    }

    // 이메일 중복 검사
    public boolean checkUserEmailExist(String email) {
        String user_name = userDao.checkUserEmailExist(email);
        return user_name == null;
    }

    // 닉네임 중복 검사
    public boolean checkUserNicknameExist(String user_nickname) {
        String user_name = userDao.checkUserNicknameExist(user_nickname);
        return user_name == null;
    }

    // 회원가입 시 정보 넣기
    public void addUserInfo(UserBean joinUserBean) {
        userDao.addUserInfo(joinUserBean);
    }

    // 아이디 찾기
    public String findUserId(String user_name, String email, String phone) {
        return userDao.findUserId(user_name, email, phone);
    }

    // 비밀번호 찾기
    public String findUserPassword(String user_id, String user_name, String email) {
        return userDao.findUserPassword(user_id, user_name, email);
    }

    // 로그인 시 정보 확인
    public boolean checkUser(UserBean loginUserBean) {
        UserBean dbUserBean = userDao.getLoginUserInfo(loginUserBean.getUser_id());
        if (dbUserBean != null && dbUserBean.getUser_pw().equals(loginUserBean.getUser_pw())) {
            loginUserBean.setUser_idx(dbUserBean.getUser_idx());
            loginUserBean.setEmail(dbUserBean.getEmail());
            loginUserBean.setPhone(dbUserBean.getPhone());
            loginUserBean.setUser_name(dbUserBean.getUser_name());
            loginUserBean.setUser_nickname(dbUserBean.getUser_nickname());
            loginUserBean.setAddress(dbUserBean.getAddress());
            loginUserBean.setDetailaddress(dbUserBean.getDetailaddress());
            loginUserBean.setAddressnum(dbUserBean.getAddressnum());
            return true;
        }
        return false;
    }

    // 비밀번호 확인
    public boolean checkPassword(String userid, String password) {
        UserBean dbUserBean = userDao.getLoginUserInfo(userid);
        return dbUserBean != null && dbUserBean.getUser_pw().equals(password);
    }

    // 수정된 메서드: UserBean을 반환하고, user_id를 인자로 받음
    public UserBean getUserInfo(UserBean loginUserBean) {
        return userDao.getLoginUserInfo(loginUserBean.getUser_id());
    }

    // 정보 수정 메서드
    public void modifyUserInfo(UserBean modifyUserBean, int user_idx) {
        modifyUserBean.setUser_idx(user_idx);
        userDao.modifyUserInfo(modifyUserBean);
    }
    
    //회원탈퇴 메서드
    public void deleteUser(int user_idx) {
        userDao.deleteUser(user_idx);
    }
    
//////////////추가된 부분 //////////////
    public UserBean getUserByIdx(int user_idx){
    	return userDao.getUserByIdx(user_idx);
    }
    
    public boolean checkGoogleUserIDExist(String user_id) {
        String user_name = userDao.checkGoogleUserIDExist(user_id);
        return user_name == null;
    }

    @Transactional
    public void addGoogleUserInfo(String user_id, String user_pw, String user_email, String user_name) {
        userMapper.addGoogleUserInfo(user_id, user_pw, user_email, user_name);
        
        loginUserBean.setUser_id(user_id);
        loginUserBean.setUser_pw(user_pw);
        loginUserBean.setEmail(user_email);
        loginUserBean.setUser_name(user_name);
        loginUserBean.setUserLogin(true); // 로그인 상태로 변경
        System.out.println("UserBean updated in service");
    }

    @Transactional
    public void updateGoogleUserInfo(String user_id, String user_name, String user_pw) {
        userMapper.updateGoogleUserInfo(user_id, user_name, user_pw);
    }

	
	
}
