package dto;

public class CartDTO_admin {
    private int cart_id;
    private int user_idx;
    private int book_id;
    private int cart_quantity;
    
    // 책 이름
    private String book_name;
    
    // 책 이미지 (url)
    private String book_pic;

    // Getters and Setters
    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getUser_idx() {
        return user_idx;
    }

    public void setUser_idx(int user_idx) {
        this.user_idx = user_idx;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getCart_quantity() {
        return cart_quantity;
    }

    public void setCart_quantity(int cart_quantity) {
        this.cart_quantity = cart_quantity;
    }

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_pic() {
		return book_pic;
	}

	public void setBook_pic(String book_pic) {
		this.book_pic = book_pic;
	}
    
    
    
}
