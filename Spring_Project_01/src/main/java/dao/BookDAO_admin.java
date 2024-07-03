package dao;

import dto.BookDTO_admin;
import filter.BookFilterDTO_admin;
import filter.UserBookFilterDTO_admin;
import mapper.BookMapper_admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAO_admin {

    @Autowired
    private BookMapper_admin bookMapper;

    public BookDTO_admin getBookById(int book_id) {
        return bookMapper.getBookById(book_id);
    }

    public List<BookDTO_admin> getAllBooks() {
        return bookMapper.getAllBooks();
    }

    public void insertBook(BookDTO_admin book) {
        bookMapper.insertBook(book);
    }
    
    public void updateBook(BookDTO_admin book) {
        bookMapper.updateBook(book);
    }

    public void deleteBook(int book_id) {
        bookMapper.deleteBook(book_id);
    }

    public List<BookDTO_admin> getBooksByFilterAndSort(BookFilterDTO_admin filter) {
        return bookMapper.getBooksByFilterAndSort(filter);
    }

    public int getBookCountByFilter(BookFilterDTO_admin filter) {
        return bookMapper.getBookCountByFilter(filter);
    }
    
    // 책 이름을 가져오는 메서드
    public String getBookNameById(int book_id) {
    	return bookMapper.getBookNameById(book_id);
    }
    
    // 책 사진(url)을 가져오는 메서드
    public String getBookPicById(int book_id) {
    	return bookMapper.getBookPicById(book_id);
    }
    

    // 유저의 책 검색 메서드
    public List<BookDTO_admin> searchBooks(UserBookFilterDTO_admin filter) {
        return bookMapper.searchBooks(filter);
    }
    
    // 유저의 책 검색 결과 수

    public int countSearchBooks(UserBookFilterDTO_admin filter) {
        return bookMapper.countSearchBooks(filter);
    }
    
    
}
