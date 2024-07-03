package mapper;

import dto.UserInfoDTO_admin;
import filter.UserInfoFilterDTO_admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserInfoMapper_admin {

    @Select("SELECT * FROM user_info WHERE user_idx = #{user_idx}")
    UserInfoDTO_admin getUserById(int user_idx);

    @Select("SELECT * FROM user_info WHERE user_id = #{user_id}")
    UserInfoDTO_admin getUserByLoginId(String user_id);

    @Select("SELECT * FROM user_info")
    List<UserInfoDTO_admin> getAllUsers();

    @Update("UPDATE user_info SET user_name = #{user_name}, user_nickname = #{user_nickname}, address = #{address}, detailaddress = #{detailaddress}, addressnum = #{addressnum}, phone = #{phone}, email = #{email} WHERE user_idx = #{user_idx}")
    void updateUser(UserInfoDTO_admin user);

    @Delete("DELETE FROM user_info WHERE user_idx = #{user_idx}")
    void deleteUser(int user_idx);

    @Select({
        "<script>",
        "SELECT * FROM (",
        "  SELECT u.*, ROW_NUMBER() OVER (",
        "    ORDER BY",
        "      <choose>",
        "        <when test='userIdxOrder != null'>user_idx ${userIdxOrder}</when>",
        "        <when test='userNameOrder != null'>user_name ${userNameOrder}</when>",
        "        <when test='userNicknameOrder != null'>user_nickname ${userNicknameOrder}</when>",
        "        <when test='userIdOrder != null'>user_id ${userIdOrder}</when>",
        "        <when test='addressOrder != null'>address ${addressOrder}</when>",
        "        <when test='detailAddressOrder != null'>detailaddress ${detailAddressOrder}</when>",
        "        <when test='addressNumOrder != null'>addressnum ${addressNumOrder}</when>",
        "        <when test='phoneOrder != null'>phone ${phoneOrder}</when>",
        "        <when test='emailOrder != null'>email ${emailOrder}</when>",
        "        <otherwise>user_idx ASC</otherwise>",
        "      </choose>",
        "  ) AS rn",
        "  FROM user_info u",
        "  WHERE 1=1",
        "  <if test='user_idx != null'>AND user_idx = #{user_idx}</if>",
        "  <if test='user_name != null'>AND user_name LIKE '%' || #{user_name} || '%'</if>",
        "  <if test='user_nickname != null'>AND user_nickname LIKE '%' || #{user_nickname} || '%'</if>",
        "  <if test='user_id != null'>AND user_id LIKE '%' || #{user_id} || '%'</if>",
        "  <if test='address != null'>AND address LIKE '%' || #{address} || '%'</if>",
        "  <if test='detailaddress != null'>AND detailaddress LIKE '%' || #{detailaddress} || '%'</if>",
        "  <if test='addressnum != null'>AND addressnum LIKE '%' || #{addressnum} || '%'</if>",
        "  <if test='phone != null'>AND phone LIKE '%' || #{phone} || '%'</if>",
        "  <if test='email != null'>AND email LIKE '%' || #{email} || '%'</if>",
        ") u",
        "WHERE rn &gt; #{offset} AND rn &lt;= #{offset} + #{pageSize}",
        "</script>"
    })
    List<UserInfoDTO_admin> getUsersByFilterAndSort(UserInfoFilterDTO_admin filter);

    @Select({
        "<script>",
        "SELECT COUNT(*) FROM user_info",
        "WHERE 1=1",
        "  <if test='user_idx != null'>AND user_idx = #{user_idx}</if>",
        "  <if test='user_name != null'>AND user_name LIKE '%' || #{user_name} || '%'</if>",
        "  <if test='user_nickname != null'>AND user_nickname LIKE '%' || #{user_nickname} || '%'</if>",
        "  <if test='user_id != null'>AND user_id LIKE '%' || #{user_id} || '%'</if>",
        "  <if test='address != null'>AND address LIKE '%' || #{address} || '%'</if>",
        "  <if test='detailaddress != null'>AND detailaddress LIKE '%' || #{detailaddress} || '%'</if>",
        "  <if test='addressnum != null'>AND addressnum LIKE '%' || #{addressnum} || '%'</if>",
        "  <if test='phone != null'>AND phone LIKE '%' || #{phone} || '%'</if>",
        "  <if test='email != null'>AND email LIKE '%' || #{email} || '%'</if>",
        "</script>"
    })
    int getUserCountByFilter(UserInfoFilterDTO_admin filter);
    
    // 닉네임 중복 체크
    @Select("SELECT COUNT(*) FROM user_info WHERE user_nickname = #{user_nickname}")
    int checkNicknameExists(String user_nickname);
    
    // 닉네임을 가져오는 메서드
    @Select("SELECT user_nickname FROM user_info WHERE user_idx = #{user_idx}")
    String getUserNicknameByUserIdx(int user_idx);

    
    // 사진을 가져오는 메서드
    @Select("SELECT user_pic FROM customer WHERE user_idx = #{user_idx}")
    String getUserPicByUserIdx(int user_idx);
    
    
}
