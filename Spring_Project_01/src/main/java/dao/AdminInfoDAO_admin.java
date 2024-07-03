package dao;

import dto.AdminInfoDTO_admin;
import filter.AdminInfoFilterDTO_admin;
import mapper.AdminInfoMapper_admin;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminInfoDAO_admin {

    @Autowired
    private AdminInfoMapper_admin adminInfoMapper;

    public AdminInfoDTO_admin getAdminById(int admin_idx) {
        return adminInfoMapper.getAdminById(admin_idx);
    }

    public AdminInfoDTO_admin getAdminByLoginId(String login_id) {
        return adminInfoMapper.getAdminByLoginId(login_id);
    }
    
    public String getAdminPicById(int admin_idx) {
        return adminInfoMapper.getAdminPicById(admin_idx);
    }

    public List<AdminInfoDTO_admin> getAllAdmins() {
        return adminInfoMapper.getAllAdmins();
    }

    public void insertAdmin(AdminInfoDTO_admin admin) {
        adminInfoMapper.insertAdmin(admin);
    }

    public void updateAdmin(AdminInfoDTO_admin admin) {
        adminInfoMapper.updateAdmin(admin);
    }

    public void deleteAdminPic(AdminInfoDTO_admin admin) {
        adminInfoMapper.deleteAdminPic(admin);
    }

    public void deleteAdmin(int admin_idx) {
        adminInfoMapper.deleteAdmin(admin_idx);
    }

    public List<AdminInfoDTO_admin> getAdminsByFilterAndSort(AdminInfoFilterDTO_admin filter) {
        return adminInfoMapper.getAdminsByFilterAndSort(filter);
    }

    public int getAdminCountByFilter(AdminInfoFilterDTO_admin filter) {
        return adminInfoMapper.getAdminCountByFilter(filter);
    }

    // 닉네임 중복 체크
    public boolean checkNicknameExists(String admin_nickname) {
        return adminInfoMapper.checkNicknameExists(admin_nickname) > 0;
    }

    // 로그인 아이디 중복 체크
    public boolean checkLoginIdExists(String login_id) {
        return adminInfoMapper.checkLoginIdExists(login_id) > 0;
    }

    // 닉네임을 가져오는 메서드
    public String getAdminNicknameByAdminIdx(int admin_idx) {
        return adminInfoMapper.getAdminNicknameByAdminIdx(admin_idx);
    }
    
    // 로그인 아이디를 가져오는 메서드
    public String getAdminLoginIdByAdminIdx(int admin_idx) {
    	return adminInfoMapper.getAdminLoginIdByAdminIdx(admin_idx);
    }
    
}
