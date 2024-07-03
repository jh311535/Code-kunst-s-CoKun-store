package kr.co.soldesk.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderBean {
	private int order_id;
    private int user_idx;
    private List<Integer> book_ids;
    private List<Integer> order_quantities; 
    private List<Integer> cart_ids;
    private int book_id;
    private int order_quantity;
    private int order_number;
    private int order_price;
    private String orderer_name;
    private String addressnum;
    private String address;
    private String detailaddress;
    private String phone;
    private String email;
    private String request;
    private String request_text;
    private String delivery_state;
    private Date order_date;
    private String book_name;
    private String book_pic;
    private int book_price;
    
    public String getFormattedOrderDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(order_date);
    }
    
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public List<Integer> getCart_ids() {
		return cart_ids;
	}
	public void setCart_ids(List<Integer> cart_ids) {
		this.cart_ids = cart_ids;
	}
	public List<Integer> getBook_ids() {
		return book_ids;
	}
	public void setBook_ids(List<Integer> book_ids) {
		this.book_ids = book_ids;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public int getOrder_quantity() {
		return order_quantity;
	}
	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}
	public List<Integer> getOrder_quantities() {
		return order_quantities;
	}
	public void setOrder_quantities(List<Integer> order_quantities) {
		this.order_quantities = order_quantities;
	}
	public int getOrder_price() {
		return order_price;
	}
	public void setOrder_price(int order_price) {
		this.order_price = order_price;
	}
	public String getOrderer_name() {
		return orderer_name;
	}
	public void setOrderer_name(String orderer_name) {
		this.orderer_name = orderer_name;
	}
	public String getAddressnum() {
		return addressnum;
	}
	public void setAddressnum(String addressnum) {
		this.addressnum = addressnum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetailaddress() {
		return detailaddress;
	}
	public void setDetailaddress(String detailaddress) {
		this.detailaddress = detailaddress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getDelivery_state() {
		return delivery_state;
	}
	public void setDelivery_state(String delivery_state) {
		this.delivery_state = delivery_state;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public int getOrder_number() {
		return order_number;
	}
	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}
	public String getRequest_text() {
		return request_text;
	}
	public void setRequest_text(String request_text) {
		this.request_text = request_text;
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

	public int getBook_price() {
		return book_price;
	}

	public void setBook_price(int book_price) {
		this.book_price = book_price;
	}    
	
	
}
