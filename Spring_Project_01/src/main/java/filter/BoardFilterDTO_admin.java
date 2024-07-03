package filter;

public class BoardFilterDTO_admin {
    private Integer board_id;
    private Integer user_idx;
    private String board_content;
    private String startDate;
    private String endDate;
    private Integer minViews;
    private Integer maxViews;
    private String board_title;
    private Integer board_info_idx;
    private int offset;
    private int pageSize;
    private String boardIdOrder;
    private String userIdxOrder;
    private String contentOrder;
    private String dateOrder;
    private String viewsOrder;
    private String titleOrder;
    private String boardInfoIdxOrder;
    private String picOrder;

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
}
