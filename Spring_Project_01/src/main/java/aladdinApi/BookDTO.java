package aladdinApi;
//완

public class BookDTO { 

    int book_id; // 기본키
    String book_name; // 책이름
    String isbn; // 책고유번호 isbn 11->13 으로 변경
    int book_price; // 가격 
    String publish_date; // 출판일
    String book_pic; // 책사진
    String book_info; // 정보 (2000)
    int inventory; // 책 재고   //-->XML에 책재고 정보가 없어서 삭제해야할듯 아니면 임의의값 넣어야함
    String publisher; // 퍼블리셔; 길이변경 30->300
    String book_category; // 책카테고리
    String author; // 저자; 길이변경 30->300!

    // Getter and Setter methods

  

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }


    public int getBook_price() {
        return book_price;
    }

    public void setBook_price(int book_price) {
        this.book_price = book_price;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getBook_pic() {
        return book_pic;
    }

    public void setBook_pic(String book_pic) {
        this.book_pic = book_pic;
    }

    public String getBook_info() {
        return book_info;
    }

    public void setBook_info(String book_info) {
        this.book_info = book_info;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getBook_category() {
        return book_category;
    }

    public void setBook_category(String book_category) {
        this.book_category = book_category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
