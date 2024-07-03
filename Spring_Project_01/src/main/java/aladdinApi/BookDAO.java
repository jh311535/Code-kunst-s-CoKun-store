package aladdinApi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookDAO {

    String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
    String user = "codekunst";
    String pass = "12345";
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;

    public BookDAO() {
        // 삭제용
    }

    public void getCon() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            this.con = DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (Exception var1) {
            var1.printStackTrace();
            System.out.println("getCon");
        } // catch
    } // getCon()

    public void insertBook(BookDTO bDto) {
        try {
            this.getCon(); // 연결!
            //--------------------
            // 변경된 부분: book_id를 book_seq.NEXTVAL로 설정
            //--------------------
            String sql = "insert into book (book_id, book_name, isbn, book_price, publish_date, book_pic, book_info, inventory, publisher, book_category, author) values(book_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
            pstmt = this.con.prepareStatement(sql);
            //--------------------
            
            pstmt.setString(1, bDto.getBook_name());
            pstmt.setString(2, bDto.getIsbn());
            pstmt.setInt(3, bDto.getBook_price());
            pstmt.setString(4, bDto.getPublish_date());
            pstmt.setString(5, bDto.getBook_pic());
            pstmt.setString(6, bDto.getBook_info());
            // 변경 전 코드 pstmt.setInt(7, bDto.getInventory());
            pstmt.setInt(7, 500); // 기본 재고 500권
            pstmt.setString(8, bDto.getPublisher());
            pstmt.setString(9, bDto.getBook_category());
            pstmt.setString(10, bDto.getAuthor());

            this.pstmt.executeUpdate();
            System.out.println("db insert 성공");

        } catch (Exception var2) {
            var2.printStackTrace();
            System.out.println("insertBook 오류");
        }
    } // insertBook
} // BookDAO
