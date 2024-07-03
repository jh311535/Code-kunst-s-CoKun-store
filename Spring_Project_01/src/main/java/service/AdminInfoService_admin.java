package service;

import dao.AdminInfoDAO_admin;
import dto.AdminInfoDTO_admin;
import filter.AdminInfoFilterDTO_admin;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@PropertySource("/WEB-INF/properties/admin_option.properties")
public class AdminInfoService_admin {

    @Autowired
    private AdminInfoDAO_admin adminInfoDAO;

    @Value("${path.upload}")
    private String path_upload;

    public AdminInfoDTO_admin getAdminById(int admin_idx) {
        return adminInfoDAO.getAdminById(admin_idx);
    }

    public AdminInfoDTO_admin getAdminByLoginId(String login_id) {
        return adminInfoDAO.getAdminByLoginId(login_id);
    }

    public List<AdminInfoDTO_admin> getAllAdmins() {
        return adminInfoDAO.getAllAdmins();
    }

    public void insertAdmin(AdminInfoDTO_admin admin) {
        MultipartFile upload_file = admin.getUpload_file();
        if (upload_file != null && !upload_file.isEmpty()) {
            String file_name = saveUploadFile(upload_file);
            admin.setAdmin_pic(file_name);
        }
        adminInfoDAO.insertAdmin(admin);
    }

    public void updateAdmin(AdminInfoDTO_admin admin, boolean deletePic) {
        if (deletePic) {
            deleteExistingPic(admin.getAdmin_pic());
            adminInfoDAO.deleteAdminPic(admin);
        } else {
            MultipartFile upload_file = admin.getUpload_file();
            if (upload_file != null && !upload_file.isEmpty()) {
                String file_name = saveUploadFile(upload_file);
                admin.setAdmin_pic(file_name);
            }
        }
        adminInfoDAO.updateAdmin(admin);
    }

    public void deleteAdmin(int admin_idx) {
        String fileName = adminInfoDAO.getAdminPicById(admin_idx);
        if (fileName != null) {
            deleteExistingPic(fileName);
        }
        adminInfoDAO.deleteAdmin(admin_idx);
    }

    public List<AdminInfoDTO_admin> getAdminsByFilterAndSort(AdminInfoFilterDTO_admin filter) {
        return adminInfoDAO.getAdminsByFilterAndSort(filter);
    }

    public int getAdminCountByFilter(AdminInfoFilterDTO_admin filter) {
        return adminInfoDAO.getAdminCountByFilter(filter);
    }

    private String saveUploadFile(MultipartFile upload_file) {
        String file_name = System.currentTimeMillis() + "_"
                + FilenameUtils.getBaseName(upload_file.getOriginalFilename()) + "."
                + FilenameUtils.getExtension(upload_file.getOriginalFilename());

        try {
            upload_file.transferTo(new File(path_upload + "/" + file_name));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file_name;
    }

    // 닉네임 중복 체크
    public boolean checkNicknameExists(String admin_nickname, Integer admin_idx) {
        if (admin_idx != null) {
            String existingNickname = adminInfoDAO.getAdminNicknameByAdminIdx(admin_idx);
            if (existingNickname.equals(admin_nickname)) {
                return false;
            }
        }
        return adminInfoDAO.checkNicknameExists(admin_nickname);
    }

    // 로그인 아이디 중복 체크
    public boolean checkLoginIdExists(String login_id, Integer admin_idx) {
        if (admin_idx != null) {
            String existingLoginId = adminInfoDAO.getAdminLoginIdByAdminIdx(admin_idx);
            if (existingLoginId.equals(login_id)) {
                return false;
            }
        }
        return adminInfoDAO.checkLoginIdExists(login_id);
    }

    // 닉네임을 가져오는 메서드
    public String getAdminNicknameByAdminIdx(int admin_idx) {
        return adminInfoDAO.getAdminNicknameByAdminIdx(admin_idx);
    }
    
    // 로그인 아이디를 가져오는 메서드
    public String getAdminLoginIdByAdminIdx(int admin_idx) {
    	return adminInfoDAO.getAdminLoginIdByAdminIdx(admin_idx);
    }

    // 존재하는 사진을 지우는 메서드
    private void deleteExistingPic(String fileName) {
        if (fileName != null && !fileName.isEmpty()) {
            File file = new File(path_upload + "/" + fileName);
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
