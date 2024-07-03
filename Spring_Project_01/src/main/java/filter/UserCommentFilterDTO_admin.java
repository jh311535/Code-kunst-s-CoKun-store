package filter;

public class UserCommentFilterDTO_admin {
    private Integer comment_id;
    private Integer board_id;
    private Integer user_idx;
    private String comment_content;
    private String start_date;
    private String end_date;
    private String board_title; // 게시글 제목
    private int offset;
    private int pageSize;
    private String commentIdOrder;
    private String boardIdOrder;
    private String userIdxOrder;
    private String contentOrder;
    private String dateOrder;
    private String titleOrder; // 게시글 제목

    // Getters and Setters
    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

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

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getBoard_title() {
        return board_title;
    }

    public void setBoard_title(String board_title) {
        this.board_title = board_title;
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

    public String getCommentIdOrder() {
        return commentIdOrder;
    }

    public void setCommentIdOrder(String commentIdOrder) {
        this.commentIdOrder = commentIdOrder;
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

    public String getTitleOrder() {
        return titleOrder;
    }

    public void setTitleOrder(String titleOrder) {
        this.titleOrder = titleOrder;
    }
}
