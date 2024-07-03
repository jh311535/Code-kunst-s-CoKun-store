package filter;

public class BookFilterDTO_admin {
    private Integer book_id;
    private String book_name;
    private Long isbn;
    private String publisher;
    private String book_category;
    private String author;
    private String startDate; // 기간 필터링 (시작 날짜)
    private String endDate; // 기간 필터링 (종료 날짜)
    private Integer minPrice; // 최소 가격
    private Integer maxPrice; // 최대 가격
    private Integer minInventory; // 최소 재고
    private Integer maxInventory; // 최대 재고
    private int offset;
    private int pageSize;
    private String bookIdOrder;
    private String bookNameOrder;
    private String isbnOrder;
    private String priceOrder;
    private String publishDateOrder;
    private String publisherOrder;
    private String categoryOrder;
    private String authorOrder;
    private String inventoryOrder;

    // Getters and Setters
    public Integer getBook_id() { return book_id; }
    public void setBook_id(Integer book_id) { this.book_id = book_id; }

    public String getBook_name() { return book_name; }
    public void setBook_name(String book_name) { this.book_name = book_name; }

    public Long getIsbn() { return isbn; }
    public void setIsbn(Long isbn) { this.isbn = isbn; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getBook_category() { return book_category; }
    public void setBook_category(String book_category) { this.book_category = book_category; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public Integer getMinPrice() { return minPrice; }
    public void setMinPrice(Integer minPrice) { this.minPrice = minPrice; }

    public Integer getMaxPrice() { return maxPrice; }
    public void setMaxPrice(Integer maxPrice) { this.maxPrice = maxPrice; }

    public Integer getMinInventory() { return minInventory; }
    public void setMinInventory(Integer minInventory) { this.minInventory = minInventory; }

    public Integer getMaxInventory() { return maxInventory; }
    public void setMaxInventory(Integer maxInventory) { this.maxInventory = maxInventory; }

    public int getOffset() { return offset; }
    public void setOffset(int offset) { this.offset = offset; }

    public int getPageSize() { return pageSize; }
    public void setPageSize(int pageSize) { this.pageSize = pageSize; }

    public String getBookIdOrder() { return bookIdOrder; }
    public void setBookIdOrder(String bookIdOrder) { this.bookIdOrder = bookIdOrder; }

    public String getBookNameOrder() { return bookNameOrder; }
    public void setBookNameOrder(String bookNameOrder) { this.bookNameOrder = bookNameOrder; }

    public String getIsbnOrder() { return isbnOrder; }
    public void setIsbnOrder(String isbnOrder) { this.isbnOrder = isbnOrder; }

    public String getPriceOrder() { return priceOrder; }
    public void setPriceOrder(String priceOrder) { this.priceOrder = priceOrder; }

    public String getPublishDateOrder() { return publishDateOrder; }
    public void setPublishDateOrder(String publishDateOrder) { this.publishDateOrder = publishDateOrder; }

    public String getPublisherOrder() { return publisherOrder; }
    public void setPublisherOrder(String publisherOrder) { this.publisherOrder = publisherOrder; }

    public String getCategoryOrder() { return categoryOrder; }
    public void setCategoryOrder(String categoryOrder) { this.categoryOrder = categoryOrder; }

    public String getAuthorOrder() { return authorOrder; }
    public void setAuthorOrder(String authorOrder) { this.authorOrder = authorOrder; }

    public String getInventoryOrder() { return inventoryOrder; }
    public void setInventoryOrder(String inventoryOrder) { this.inventoryOrder = inventoryOrder; }
}
