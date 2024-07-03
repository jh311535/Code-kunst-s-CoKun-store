package aladdinApi;
import java.util.Date;

public class TestInsertBook {

	//테스트용
    public static void main(String[] args) {
        BookDTO book = new BookDTO();
        book.setBook_id(2);
        book.setBook_name("이도형");
        book.setIsbn("1234567890");
        book.setBook_price(1999);
        book.setPublish_date("2024/02/02");
        book.setBook_pic("sample.jpg");
        book.setBook_info("이도형이쓴책");
        book.setInventory(100);
        book.setPublisher("퍼블리셔");
        book.setBook_category("코딩");
        book.setAuthor("책저자1");

        BookDAO bookDAO = new BookDAO();
        bookDAO.insertBook(book);

        System.out.println("성공!");
    }
}
