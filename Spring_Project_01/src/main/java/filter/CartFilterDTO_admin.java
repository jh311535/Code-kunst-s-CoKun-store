package filter;

public class CartFilterDTO_admin {
    private Integer cart_id;
    private Integer user_idx;
    private Integer book_id;
    private Integer cart_quantity_min;
    private Integer cart_quantity_max;
    
    // Sorting fields
    private String cartIdOrder;
    private String userIdxOrder;
    private String bookIdOrder;
    private String cartQuantityOrder;
    
    // Pagination fields
    private int offset;
    private int pageSize;

    // Getters and Setters
    public Integer getCart_id() {
        return cart_id;
    }

    public void setCart_id(Integer cart_id) {
        this.cart_id = cart_id;
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

    public Integer getCart_quantity_min() {
        return cart_quantity_min;
    }

    public void setCart_quantity_min(Integer cart_quantity_min) {
        this.cart_quantity_min = cart_quantity_min;
    }

    public Integer getCart_quantity_max() {
        return cart_quantity_max;
    }

    public void setCart_quantity_max(Integer cart_quantity_max) {
        this.cart_quantity_max = cart_quantity_max;
    }

    public String getCartIdOrder() {
        return cartIdOrder;
    }

    public void setCartIdOrder(String cartIdOrder) {
        this.cartIdOrder = cartIdOrder;
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

    public String getCartQuantityOrder() {
        return cartQuantityOrder;
    }

    public void setCartQuantityOrder(String cartQuantityOrder) {
        this.cartQuantityOrder = cartQuantityOrder;
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
