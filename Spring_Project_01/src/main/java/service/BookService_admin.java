package service;

import dao.BookDAO_admin;
import dto.BookDTO_admin;
import filter.BookFilterDTO_admin;
import filter.UserBookFilterDTO_admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@PropertySource("/WEB-INF/properties/admin_option.properties")
public class BookService_admin {

    @Autowired
    private BookDAO_admin bookDAO;

    @Value("${path.upload}")
    private String path_upload;

    public BookDTO_admin getBookById(int book_id) {
        return bookDAO.getBookById(book_id);
    }
    
    public void insertBook(BookDTO_admin book) {
        bookDAO.insertBook(book);
    }

    public List<BookDTO_admin> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public void updateBook(BookDTO_admin book) {
        bookDAO.updateBook(book);
    }
    
    public void deleteBook(int book_id) {
        bookDAO.deleteBook(book_id);
    }

    public List<BookDTO_admin> getBooksByFilterAndSort(BookFilterDTO_admin filter) {
        return bookDAO.getBooksByFilterAndSort(filter);
    }

    public int getBookCountByFilter(BookFilterDTO_admin filter) {
        return bookDAO.getBookCountByFilter(filter);
    }
    
    // 책 이름을 가져오는 메서드
    public String getBookNameById(int book_id) {
    	return bookDAO.getBookNameById(book_id);
    }
    
    // 책 사진(url)을 가져오는 메서드
    public String getBookPicById(int book_id) {
    	return bookDAO.getBookPicById(book_id);
    }
    

    // 유저의 책 검색 메서드
    public List<BookDTO_admin> searchBooks(UserBookFilterDTO_admin filter) {
        return bookDAO.searchBooks(filter);
    }
    
    // 유저의 책 검색 결과 수
    public int countSearchBooks(UserBookFilterDTO_admin filter) {
        return bookDAO.countSearchBooks(filter);
    }
    
    
}
