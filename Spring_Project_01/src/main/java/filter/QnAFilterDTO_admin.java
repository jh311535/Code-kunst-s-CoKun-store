package filter;

public class QnAFilterDTO_admin {
    private int offset;
    private int pageSize;
    
    // QnAComment 필터 기준
    private Integer board_id;
    private Integer user_idx;
    private String board_content;
    private String startDate;
    private String endDate;
    private Integer minViews;
    private Integer maxViews;
    private String board_title;
    private Integer board_info_idx;
    
    // QnAComment 정렬 기준
    private String boardIdOrder;
    private String userIdxOrder;
    private String contentOrder;
    private String dateOrder;
    private String viewsOrder;
    private String titleOrder;
    private String boardInfoIdxOrder;
    private String picOrder;

    // QnAComment 필터 기준
    private Integer qna_idx;
    private Integer admin_idx;
    private String qna_comment;
    private String qna_startDate;
    private String qna_endDate;

    // QnAComment 정렬 기준
    private String qnaIdOrder;
    private String adminIdxOrder;
    private String qnaCommentOrder;
    private String qnaDateOrder;

    // Getters and Setters
    public Integer getBoard_id() {
        return board_id;
    }

    public void setBoard_id(Integer board_id) {
        this.board_id = board_id;
    }

    public Integer getUser_idx() {
        return user_idx;
    }

    public void setUser_idx(Integer user_idx) {
        this.user_idx = user_idx;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
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

    public Integer getMinViews() {
        return minViews;
    }

    public void setMinViews(Integer minViews) {
        this.minViews = minViews;
    }

    public Integer getMaxViews() {
        return maxViews;
    }

    public void setMaxViews(Integer maxViews) {
        this.maxViews = maxViews;
    }

    public String getBoard_title() {
        return board_title;
    }

    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }

    public Integer getBoard_info_idx() {
        return board_info_idx;
    }

    public void setBoard_info_idx(Integer board_info_idx) {
        this.board_info_idx = board_info_idx;
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

    public String getBoardIdOrder() {
        return boardIdOrder;
    }

    public void setBoardIdOrder(String boardIdOrder) {
        this.boardIdOrder = boardIdOrder;
    }

    public String getUserIdxOrder() {
        return userIdxOrder;
    }

    public void setUserIdxOrder(String userIdxOrder) {
        this.userIdxOrder = userIdxOrder;
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

    public String getViewsOrder() {
        return viewsOrder;
    }

    public void setViewsOrder(String viewsOrder) {
        this.viewsOrder = viewsOrder;
    }

    public String getTitleOrder() {
        return titleOrder;
    }

    public void setTitleOrder(String titleOrder) {
        this.titleOrder = titleOrder;
    }

    public String getBoardInfoIdxOrder() {
        return boardInfoIdxOrder;
    }

    public void setBoardInfoIdxOrder(String boardInfoIdxOrder) {
        this.boardInfoIdxOrder = boardInfoIdxOrder;
    }

    public String getPicOrder() {
        return picOrder;
    }

    public void setPicOrder(String picOrder) {
        this.picOrder = picOrder;
    }

    public Integer getQna_idx() {
        return qna_idx;
    }

    public void setQna_idx(Integer qna_idx) {
        this.qna_idx = qna_idx;
    }

    public Integer getAdmin_idx() {
        return admin_idx;
    }

    public void setAdmin_idx(Integer admin_idx) {
        this.admin_idx = admin_idx;
    }

    public String getQna_comment() {
        return qna_comment;
    }

    public void setQna_comment(String qna_comment) {
        this.qna_comment = qna_comment;
    }

    public String getQna_startDate() {
        return qna_startDate;
    }

    public void setQna_startDate(String qna_startDate) {
        this.qna_startDate = qna_startDate;
    }

    public String getQna_endDate() {
        return qna_endDate;
    }

    public void setQna_endDate(String qna_endDate) {
        this.qna_endDate = qna_endDate;
    }

    public String getQnaIdOrder() {
        return qnaIdOrder;
    }

    public void setQnaIdOrder(String qnaIdOrder) {
        this.qnaIdOrder = qnaIdOrder;
    }

    public String getAdminIdxOrder() {
        return adminIdxOrder;
    }

    public void setAdminIdxOrder(String adminIdxOrder) {
        this.adminIdxOrder = adminIdxOrder;
    }

    public String getQnaCommentOrder() {
        return qnaCommentOrder;
    }

    public void setQnaCommentOrder(String qnaCommentOrder) {
        this.qnaCommentOrder = qnaCommentOrder;
    }

    public String getQnaDateOrder() {
        return qnaDateOrder;
    }

    public void setQnaDateOrder(String qnaDateOrder) {
        this.qnaDateOrder = qnaDateOrder;
    }
}
