package dao;

import dto.NoticeDTO_admin;
import filter.NoticeFilterDTO_admin;
import mapper.NoticeMapper_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeDAO_admin {

    @Autowired
    private NoticeMapper_admin noticeMapper;

    public NoticeDTO_admin getNoticeById(int notice_id) {
        return noticeMapper.getNoticeById(notice_id);
    }

    public String getNoticePicById(int notice_id) {
        return noticeMapper.getNoticePicById(notice_id);
    }

    public List<NoticeDTO_admin> getAllNotices() {
        return noticeMapper.getAllNotices();
    }

    public void insertNotice(NoticeDTO_admin notice) {
        noticeMapper.insertNotice(notice);
    }

    public void updateNotice(NoticeDTO_admin notice) {
        noticeMapper.updateNotice(notice);
    }

    public void deleteNoticePic(NoticeDTO_admin notice) {
        noticeMapper.deleteNoticePic(notice);
    }
    
    public void deleteNotice(int notice_id) {
        noticeMapper.deleteNotice(notice_id);
    }

    public void incrementViews(int notice_id) {
        noticeMapper.incrementViews(notice_id);
    }

    public List<NoticeDTO_admin> getNoticesByFilterAndSort(NoticeFilterDTO_admin filter) {
        return noticeMapper.getNoticesByFilterAndSort(filter);
    }

    public int getNoticeCountByFilter(NoticeFilterDTO_admin filter) {
        return noticeMapper.getNoticeCountByFilter(filter);
    }
}
