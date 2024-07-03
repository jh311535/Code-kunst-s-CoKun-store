package mapper;

import dto.AdminInfoDTO_admin;
import filter.AdminInfoFilterDTO_admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminInfoMapper_admin {

    @Select("SELECT * FROM admin_info WHERE admin_idx = #{admin_idx}")
    AdminInfoDTO_admin getAdminById(int admin_idx);

    @Select("SELECT * FROM admin_info WHERE login_id = #{login_id}")
    AdminInfoDTO_admin getAdminByLoginId(String login_id);

    @Select("SELECT admin_pic FROM admin_info WHERE admin_idx = #{admin_idx}")
    String getAdminPicById(int admin_idx);

    @Select("SELECT * FROM admin_info")
    List<AdminInfoDTO_admin> getAllAdmins();

    @Insert({
        "<script>",
        "INSERT INTO admin_info (admin_idx, admin_name, admin_nickname, login_id, login_pwd, phone, email, admin_pic, admin_type) ",
        "VALUES (admin_info_seq.NEXTVAL, #{admin_name}, #{admin_nickname}, #{login_id}, #{login_pwd}, #{phone}, #{email}, ",
        "<if test='admin_pic != null'>#{admin_pic}</if>",
        "<if test='admin_pic == null'>NULL</if>, 'level3')",
        "</script>"
    })
    void insertAdmin(AdminInfoDTO_admin admin);

    @Update({
        "<script>",
        "UPDATE admin_info ",
        "SET admin_name = #{admin_name}, ",
        "    admin_nickname = #{admin_nickname}, ",
        "    phone = #{phone}, ",
        "    email = #{email}, ",
        "<if test='admin_pic != null'>admin_pic = #{admin_pic}, </if>",
        "<if test='login_pwd != null and login_pwd != \"\"'>login_pwd = #{login_pwd} </if>",
        "WHERE admin_idx = #{admin_idx}",
        "</script>"
    })
    void updateAdmin(AdminInfoDTO_admin admin);

    @Update({
        "<script>",
        "UPDATE admin_info ",
        "SET admin_pic = null ",
        "WHERE admin_idx = #{admin_idx}",
        "</script>"
    })
    void deleteAdminPic(AdminInfoDTO_admin admin);

    @Delete("DELETE FROM admin_info WHERE admin_idx = #{admin_idx}")
    void deleteAdmin(int admin_idx);

    @Select({
        "<script>",
        "SELECT * FROM (",
        "  SELECT a.*, ROW_NUMBER() OVER (",
        "    ORDER BY",
        "      <choose>",
        "        <when test='adminIdxOrder != null'>admin_idx ${adminIdxOrder}</when>",
        "        <when test='adminNameOrder != null'>admin_name ${adminNameOrder}</when>",
        "        <when test='nickNameOrder != null'>admin_nickname ${nickNameOrder}</when>",
        "        <when test='loginIdOrder != null'>login_id ${loginIdOrder}</when>",
        "        <when test='adminTypeOrder != null'>admin_type ${adminTypeOrder}</when>",
        "        <when test='phoneOrder != null'>phone ${phoneOrder}</when>",
        "        <when test='emailOrder != null'>email ${emailOrder}</when>",
        "        <otherwise>admin_idx ASC</otherwise>",
        "      </choose>",
        "  ) AS rn",
        "  FROM admin_info a",
        "  WHERE 1=1",
        "  <if test='admin_idx != null'>AND admin_idx = #{admin_idx}</if>",
        "  <if test='admin_name != null'>AND admin_name LIKE '%' || #{admin_name} || '%'</if>",
        "  <if test='admin_nickname != null'>AND admin_nickname LIKE '%' || #{admin_nickname} || '%'</if>",
        "  <if test='login_id != null'>AND login_id LIKE '%' || #{login_id} || '%'</if>",
        "  <if test='admin_type != null'>AND admin_type LIKE '%' || #{admin_type} || '%'</if>",
        "  <if test='phone != null'>AND phone LIKE '%' || #{phone} || '%'</if>",
        "  <if test='email != null'>AND email LIKE '%' || #{email} || '%'</if>",
        ") a",
        "WHERE rn &gt; #{offset} AND rn &lt;= #{offset} + #{pageSize}",
        "</script>"
    })
    List<AdminInfoDTO_admin> getAdminsByFilterAndSort(AdminInfoFilterDTO_admin filter);

    @Select({
        "<script>",
        "SELECT COUNT(*) FROM admin_info",
        "WHERE 1=1",
        "  <if test='admin_idx != null'>AND admin_idx = #{admin_idx}</if>",
        "  <if test='admin_name != null'>AND admin_name LIKE '%' || #{admin_name} || '%'</if>",
        "  <if test='admin_nickname != null'>AND admin_nickname LIKE '%' || #{admin_nickname} || '%'</if>",
        "  <if test='login_id != null'>AND login_id LIKE '%' || #{login_id} || '%'</if>",
        "  <if test='admin_type != null'>AND admin_type LIKE '%' || #{admin_type} || '%'</if>",
        "  <if test='phone != null'>AND phone LIKE '%' || #{phone} || '%'</if>",
        "  <if test='email != null'>AND email LIKE '%' || #{email} || '%'</if>",
        "</script>"
    })
    int getAdminCountByFilter(AdminInfoFilterDTO_admin filter);

    // 닉네임 중복 체크
    @Select("SELECT COUNT(*) FROM admin_info WHERE admin_nickname = #{admin_nickname}")
    int checkNicknameExists(String admin_nickname);

    // 로그인 아이디 중복 체크
    @Select("SELECT COUNT(*) FROM admin_info WHERE login_id = #{login_id}")
    int checkLoginIdExists(String login_id);

    // 닉네임을 가져오는 메서드
    @Select("SELECT admin_nickname FROM admin_info WHERE admin_idx = #{admin_idx}")
    String getAdminNicknameByAdminIdx(int admin_idx);
    
    // 로그인 아이디를 가져오는 메서드
    @Select("SELECT login_id FROM admin_info WHERE admin_idx = #{admin_idx}")
    String getAdminLoginIdByAdminIdx(int admin_idx);
    
    
    
    
}
