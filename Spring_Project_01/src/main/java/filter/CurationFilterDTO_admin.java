package filter;

public class CurationFilterDTO_admin {
    private Integer curation_id;
    private String curation_category;
    private String product_name;
    private int offset;
    private int pageSize;
    private String curationIdOrder;
    private String curationCategoryOrder;
    private String productNameOrder;
    private String productUrlOrder; 

    // Getters and Setters
    public Integer getCuration_id() {
        return curation_id;
    }

    public void setCuration_id(Integer curation_id) {
        this.curation_id = curation_id;
    }

    public String getCuration_category() {
        return curation_category;
    }

    public void setCuration_category(String curation_category) {
        this.curation_category = curation_category;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
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

    public String getCurationIdOrder() {
        return curationIdOrder;
    }

    public void setCurationIdOrder(String curationIdOrder) {
        this.curationIdOrder = curationIdOrder;
    }

    public String getCurationCategoryOrder() {
        return curationCategoryOrder;
    }

    public void setCurationCategoryOrder(String curationCategoryOrder) {
        this.curationCategoryOrder = curationCategoryOrder;
    }

    public String getProductNameOrder() {
        return productNameOrder;
    }

    public void setProductNameOrder(String productNameOrder) {
        this.productNameOrder = productNameOrder;
    }

	public String getProductUrlOrder() {
		return productUrlOrder;
	}

	public void setProductUrlOrder(String productUrlOrder) {
		this.productUrlOrder = productUrlOrder;
	}
    
    
}
