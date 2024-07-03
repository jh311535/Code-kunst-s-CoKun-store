package filter;

public class ReviewFilterDTO_admin {
    private Integer review_id;
    private Integer user_idx;
    private Integer book_id;
    private Double rating_min;
    private Double rating_max;
    private String review_content;
    private String startDate;
    private String endDate;

    // Sorting fields
    private String reviewIdOrder;
    private String userIdxOrder;
    private String bookIdOrder;
    private String ratingOrder;
    private String reviewDateOrder;

    // Pagination fields
    private int offset;
    private int pageSize;

    // Getters and Setters
    public Integer getReview_id() {
        return review_id;
    }

    public void setReview_id(Integer review_id) {
        this.review_id = review_id;
    }

    public Integer getUser_idx() {
        return user_idx;
    }

    public void setUser_idx(Integer user_idx) {
        this.user_idx = user_idx;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Double getRating_min() {
        return rating_min;
    }

    public void setRating_min(Double rating_min) {
        this.rating_min = rating_min;
    }

    public Double getRating_max() {
        return rating_max;
    }

    public void setRating_max(Double rating_max) {
        this.rating_max = rating_max;
    }

    public String getReview_content() {
        return review_content;
    }

    public void setReview_content(String review_content) {
        this.review_content = review_content;
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

    public String getReviewIdOrder() {
        return reviewIdOrder;
    }

    public void setReviewIdOrder(String reviewIdOrder) {
        this.reviewIdOrder = reviewIdOrder;
    }

    public String getUserIdxOrder() {
        return userIdxOrder;
    }

    public void setUserIdxOrder(String userIdxOrder) {
        this.userIdxOrder = userIdxOrder;
    }

    public String getBookIdOrder() {
        return bookIdOrder;
    }

    public void setBookIdOrder(String bookIdOrder) {
        this.bookIdOrder = bookIdOrder;
    }

    public String getRatingOrder() {
        return ratingOrder;
    }

    public void setRatingOrder(String ratingOrder) {
        this.ratingOrder = ratingOrder;
    }

    public String getReviewDateOrder() {
        return reviewDateOrder;
    }

    public void setReviewDateOrder(String reviewDateOrder) {
        this.reviewDateOrder = reviewDateOrder;
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
}
