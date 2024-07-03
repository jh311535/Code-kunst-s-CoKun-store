package kr.co.soldesk.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.soldesk.beans.UserBean;

public interface UserMapper {

    // ���̵� �ߺ� �˻�
    @Select("SELECT user_id FROM user_info WHERE user_id = #{user_id}")
    String checkUserIdExist(String user_id);

    // �̸��� �ߺ� �˻�
    @Select("SELECT email FROM user_info WHERE email = #{email}")
    String checkUserEmailExist(String email);

    // �г��� �ߺ� �˻�
    @Select("SELECT user_nickname FROM user_info WHERE user_nickname = #{user_nickname}")
    String checkUserNicknameExist(String user_nickname);

    // ȸ������ �� db�� ��
    @Insert("INSERT INTO user_info (user_idx, user_name, user_id, user_pw, email, user_nickname, phone, address, detailaddress, addressnum) " +
            "VALUES (user_seq.nextval, #{user_name}, #{user_id}, #{user_pw}, #{email}, #{user_nickname}, #{phone}, #{address}, #{detailaddress}, #{addressnum})")
    void addUserInfo(UserBean joinUserBean);

    // �α��� �� db�� ���� Ȯ��
    @Select("SELECT * FROM user_info WHERE user_id = #{user_id}")
    UserBean getLoginUserInfo(String user_id);
    
    // �α��� �� db�� ���� Ȯ��
    @Select("SELECT * FROM user_info WHERE user_idx = #{user_idx}")
    UserBean getUserInfo(int user_idx);

    // ���̵� ã��
    @Select("SELECT user_id FROM user_info WHERE user_name = #{user_name} AND email = #{email} AND phone = #{phone}")
    String findUserId(@Param("user_name") String user_name, @Param("email") String email, @Param("phone") String phone);

    // ��й�ȣ ã��
    @Select("SELECT user_pw FROM user_info WHERE user_id = #{user_id} AND user_name = #{user_name} AND email = #{email}")
    String findUserPassword(@Param("user_id") String user_id, @Param("user_name") String user_name, @Param("email") String email);


    // ���� ����
    @Update("UPDATE user_info SET user_pw = #{user_pw}, user_name = #{user_name}, phone = #{phone}, addressnum = #{addressnum}, address = #{address}, detailaddress = #{detailaddress} WHERE user_id = #{user_id}")
    void modifyUserInfo(UserBean modifyUserBean);


    // ��й�ȣ Ȯ��
    @Select("SELECT COUNT(*) FROM user_info WHERE user_id = #{user_id} AND user_pw = #{user_pw}")
    boolean checkPassword(@Param("user_id") String user_id, @Param("user_pw") String user_pw);
    
    //ȸ�� Ż��
    @Delete("DELETE FROM user_info WHERE user_idx = #{user_idx}")
    void deleteUser(int user_idx);
    
	////////////////////////// 추가한 부분 /////////////////////////////
	@Select("select USER_IDX, USER_NAME, USER_ID, USER_PW " +
			"from USER_INFO " +
			"where USER_IDX = #{user_idx}")
	UserBean getUserByIdx(int user_idx);
	
	@Insert("INSERT INTO user_info (user_idx, user_id, user_pw, email, user_name) " +
            "VALUES (user_seq.nextval, #{user_id}, #{user_pw}, #{user_email}, #{user_name})")
    void addGoogleUserInfo(@Param("user_id") String user_id, 
                           @Param("user_pw") String user_pw, 
                           @Param("user_email") String user_email, 
                           @Param("user_name") String user_name);

    @Select("SELECT user_name, user_idx FROM user_info WHERE user_id = #{user_id}")
    UserBean getGoogleLoginUserInfo(String user_id);

    @Select("SELECT user_name FROM user_info WHERE user_id = #{user_id}")
    String checkGoogleUserIDExist(String user_id);
    
    @Update("UPDATE user_info SET user_name = #{user_name}, user_pw = #{user_pw} WHERE user_id = #{user_id}")
    void updateGoogleUserInfo(@Param("user_id") String user_id, 
                              @Param("user_name") String user_name, 
                              @Param("user_pw") String user_pw);
}
