package naverApi;

public class NaverDTO {
    private int curation_id;
    private String curation_category;
    private String product_name;
    private String product_pic;
    private String product_url;




	public String getCuration_category() {
		return curation_category;
	}

	public void setCuration_category(String curation_category) {
		this.curation_category = curation_category;
	}

	// Getters and Setters
    public int getCuration_id() {
        return curation_id;
    }

    public void setCuration_id(int curation_id) {
        this.curation_id = curation_id;
    }


   

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_pic() {
        return product_pic;
    }

    public void setProduct_pic(String product_pic) {
        this.product_pic = product_pic;
    }

    public String getProduct_url() {
        return product_url;
    }

    public void setProduct_url(String product_url) {
        this.product_url = product_url;
    }


}
