package kr.co.soldesk.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import kr.co.soldesk.beans.BookDTO;
import kr.co.soldesk.mapper.BookMapper;

@Repository
public class BookDAO {
 
    @Autowired
    private BookMapper bookMapper;

    
    public List<BookDTO> getBookList() {
    	List<BookDTO> bookList = bookMapper.getBookList();
        return bookList;
    }

    public List<BookDTO> getBooksByCategories(List<String> categories) {
        return bookMapper.getBooksByCategories(categories);
    }
    
    public BookDTO getBookById(int book_id) {
        return bookMapper.getBookById(book_id);
    }
    
    public Integer getBookPrice(int book_id) {
        return bookMapper.getBookPrice(book_id);
    }

    
    
}
