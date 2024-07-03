package naverApi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NaverDAO {

    String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
    String user = "codekunst";
    String pass = "12345";
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;

    public NaverDAO() {
        // 기본 생성자
    }

    public void getCon() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            this.con = DriverManager.getConnection(this.url, this.user, this.pass);
            System.out.println("Database connection successful");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getCon 오류");
        }
    }

    public void insertProduct(NaverDTO naverDTO) {
        try {
            this.getCon(); // 연결!
            
            String sql = "INSERT INTO curation (curation_id, curation_category, product_name, product_pic, product_url) VALUES (curation_seq.NEXTVAL, ?, ?, ?, ?)";
            pstmt = this.con.prepareStatement(sql);
            
            pstmt.setString(1, naverDTO.getCuration_category());
            pstmt.setString(2, naverDTO.getProduct_name());
            pstmt.setString(3, naverDTO.getProduct_pic());
            pstmt.setString(4, naverDTO.getProduct_url());

            pstmt.executeUpdate();
            System.out.println("DB insert 성공");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("insertProduct 오류: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<NaverDTO> findAll() {
        List<NaverDTO> products = new ArrayList<>();
        String query = "SELECT * FROM curation";
        try {
            this.getCon();
            Statement stmt = this.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                NaverDTO naverDTO = new NaverDTO();
                naverDTO.setCuration_id(rs.getInt("curation_id"));
                naverDTO.setCuration_category(rs.getString("curation_category"));
                naverDTO.setProduct_name(rs.getString("product_name"));
                naverDTO.setProduct_pic(rs.getString("product_pic"));
                naverDTO.setProduct_url(rs.getString("product_url"));
                products.add(naverDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return products;
    }
}
