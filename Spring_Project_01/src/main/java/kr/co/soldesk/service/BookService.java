package kr.co.soldesk.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import kr.co.soldesk.beans.BookDTO;
import kr.co.soldesk.dao.BookDAO;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BookService {
    
    @Autowired
    private BookDAO bookDAO;

    @Value("${page.paginationcnt}")
    private int page_paginationcnt;
    
    @Value("${page.listcnt}")
    private int page_listcnt;
    
    //책
    public List<BookDTO> getBookList() {
        List<BookDTO> bookList = bookDAO.getBookList();
        return bookList;
    }
    
   
    
     //카테고리쪽 신경X
    public List<BookDTO> getBooksByCategories(List<String> categories) {
        return bookDAO.getBooksByCategories(categories);
    }
    //페이징쪽 신경X
    
    public int getPageListCnt() {
        return page_listcnt;
    }
    
    public int getPagePaginationCnt() {
        return page_paginationcnt;
    }
   
    public BookDTO getBookById(int book_id) {
        return bookDAO.getBookById(book_id);
    }
    
    public Integer getBookPrice(int book_id) {
        return bookDAO.getBookPrice(book_id);
    }
    
}
    
