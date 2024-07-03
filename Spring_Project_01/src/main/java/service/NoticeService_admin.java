package service;

import dao.NoticeDAO_admin;
import dto.NoticeDTO_admin;
import filter.NoticeFilterDTO_admin;
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
public class NoticeService_admin {

    @Autowired
    private NoticeDAO_admin noticeDAO;

    @Value("${path.upload}")
    private String path_upload;

    public NoticeDTO_admin getNoticeById(int notice_id) {
        return noticeDAO.getNoticeById(notice_id);
    }

    public List<NoticeDTO_admin> getAllNotices() {
        return noticeDAO.getAllNotices();
    }

    public void insertNotice(NoticeDTO_admin notice) {
        MultipartFile upload_file = notice.getUpload_file();
        if (upload_file != null && !upload_file.isEmpty()) {
            String file_name = saveUploadFile(upload_file);
            notice.setNotice_pic(file_name);
        }
        noticeDAO.insertNotice(notice);
    }

    public void updateNotice(NoticeDTO_admin notice, boolean deletePic) {
        if (deletePic) {
            deleteExistingPic(notice.getNotice_pic());
            noticeDAO.deleteNoticePic(notice);
        } else {
            MultipartFile upload_file = notice.getUpload_file();
            if (upload_file != null && !upload_file.isEmpty()) {
                String file_name = saveUploadFile(upload_file);
                notice.setNotice_pic(file_name);
            }
        }
        noticeDAO.updateNotice(notice);
    }

    public void deleteNotice(int notice_id) {
        String fileName = noticeDAO.getNoticePicById(notice_id);
        if (fileName != null) {
        	deleteExistingPic(fileName);
        }
        noticeDAO.deleteNotice(notice_id);
    }

    public void incrementViews(int notice_id) {
        noticeDAO.incrementViews(notice_id);
    }

    public List<NoticeDTO_admin> getNoticesByFilterAndSort(NoticeFilterDTO_admin filter) {
        return noticeDAO.getNoticesByFilterAndSort(filter);
    }

    public int getNoticeCountByFilter(NoticeFilterDTO_admin filter) {
        return noticeDAO.getNoticeCountByFilter(filter);
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

    private void deleteExistingPic(String fileName) {
        if (fileName != null && !fileName.isEmpty()) {
            File file = new File(path_upload + "/" + fileName);
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
