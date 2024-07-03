package filter;

public class UserBookFilterDTO_admin {
    private String keyword;
    private String book_name;
    private String author;
    private String book_category;
    private String publisher;
    private Integer minPrice;
    private Integer maxPrice;
    private int offset;
    private int pageSize;
    private String sortField;
    private String sortOrder;

    // Getters and Setters
    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }

    public String getBook_name() { return book_name; }
    public void setBook_name(String book_name) { this.book_name = book_name; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getBook_category() { return book_category; }
    public void setBook_category(String book_category) { this.book_category = book_category; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public Integer getMinPrice() { return minPrice; }
    public void setMinPrice(Integer minPrice) { this.minPrice = minPrice; }

    public Integer getMaxPrice() { return maxPrice; }
    public void setMaxPrice(Integer maxPrice) { this.maxPrice = maxPrice; }

    public int getOffset() { return offset; }
    public void setOffset(int offset) { this.offset = offset; }

    public int getPageSize() { return pageSize; }
    public void setPageSize(int pageSize) { this.pageSize = pageSize; }

    public String getSortField() { return sortField; }
    public void setSortField(String sortField) { this.sortField = sortField; }

    public String getSortOrder() { return sortOrder; }
    public void setSortOrder(String sortOrder) { this.sortOrder = sortOrder; }
}
