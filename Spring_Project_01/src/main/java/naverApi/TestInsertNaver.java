package naverApi;

public class TestInsertNaver {

    // 테스트용
    public static void main(String[] args) {
        NaverDTO naver = new NaverDTO();
        naver.setCuration_id(2);
        naver.setCuration_category("이도형");
        naver.setProduct_name("이도형");
        naver.setProduct_pic("1.jpg");
        naver.setProduct_url("1.com");
        
        NaverDAO naverDAO = new NaverDAO();
        naverDAO.insertProduct(naver);
        
        System.out.println("성공");
    }
}
