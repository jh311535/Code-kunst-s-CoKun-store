package filter;

public class NoticeFilterDTO_admin {
    private Integer notice_id;
    private Integer admin_idx;
    private String notice_title;
    private String notice_content;
    private String startDate;
    private String endDate;
    private int offset;
    private int pageSize;
    private String noticeIdOrder;
    private String adminIdxOrder;
    private String titleOrder;
    private String contentOrder;
    private String dateOrder;
    private String picOrder;
    private String viewsOrder;

    // Getters and Setters
    public Integer getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(Integer notice_id) {
        this.notice_id = notice_id;
    }

    public Integer getAdmin_idx() {
        return admin_idx;
    }

    public void setAdmin_idx(Integer admin_idx) {
        this.admin_idx = admin_idx;
    }

    public String getNotice_title() {
        return notice_title;
    }

    public void setNotice_title(String notice_title) {
        this.notice_title = notice_title;
    }

    public String getNotice_content() {
        return notice_content;
    }

    public void setNotice_content(String notice_content) {
        this.notice_content = notice_content;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getNoticeIdOrder() {
        return noticeIdOrder;
    }

    public void setNoticeIdOrder(String noticeIdOrder) {
        this.noticeIdOrder = noticeIdOrder;
    }

    public String getAdminIdxOrder() {
        return adminIdxOrder;
    }

    public void setAdminIdxOrder(String adminIdxOrder) {
        this.adminIdxOrder = adminIdxOrder;
    }

    public String getTitleOrder() {
        return titleOrder;
    }

    public void setTitleOrder(String titleOrder) {
        this.titleOrder = titleOrder;
    }

    public String getContentOrder() {
        return contentOrder;
    }

    public void setContentOrder(String contentOrder) {
        this.contentOrder = contentOrder;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getPicOrder() {
        return picOrder;
    }

    public void setPicOrder(String picOrder) {
        this.picOrder = picOrder;
    }

    public String getViewsOrder() {
        return viewsOrder;
    }

    public void setViewsOrder(String viewsOrder) {
        this.viewsOrder = viewsOrder;
    }
}
