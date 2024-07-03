package controller;

import dto.BookDTO_admin;
import dto.PageBean_admin;
import filter.UserBookFilterDTO_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.BookService_admin;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/search")
public class UserSearchController_admin {

    @Autowired
    private BookService_admin bookService;

    @GetMapping("/results")
    public String searchBooks(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "keyword", required = false) String keyword,
                              @RequestParam(value = "sortField", required = false) String sortField,
                              @RequestParam(value = "sortOrder", required = false) String sortOrder,
                              @RequestParam(value = "newSearch", required = false) String newSearch,
                              @RequestParam(value = "book_name", required = false) String bookName,
                              @RequestParam(value = "author", required = false) String author,
                              @RequestParam(value = "book_category", required = false) String bookCategory,
                              @RequestParam(value = "publisher", required = false) String publisher,
                              @RequestParam(value = "minPrice", required = false) Integer minPrice,
                              @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
                              @ModelAttribute UserBookFilterDTO_admin filter,
                              Model model, HttpSession session) {

        if ("true".equals(newSearch)) {
            filter = new UserBookFilterDTO_admin();
            filter.setKeyword(keyword);
            session.setAttribute("userBookFilter", filter);
        } else {
            filter = (UserBookFilterDTO_admin) session.getAttribute("userBookFilter");
            if (filter == null) {
                filter = new UserBookFilterDTO_admin();
                filter.setKeyword(keyword);
                session.setAttribute("userBookFilter", filter);
            }
        }

        filter.setBook_name(bookName);
        filter.setAuthor(author);
        filter.setBook_category(bookCategory);
        filter.setPublisher(publisher);
        filter.setMinPrice(minPrice);
        filter.setMaxPrice(maxPrice);

        int pageSize = 10;
        int offset = (page - 1) * pageSize;
        filter.setOffset(offset);
        filter.setPageSize(pageSize);
        filter.setSortField(sortField);
        filter.setSortOrder(sortOrder);

        List<BookDTO_admin> bookList = bookService.searchBooks(filter);
        int totalBooks = bookService.countSearchBooks(filter);
        model.addAttribute("totalBooks", totalBooks);
        PageBean_admin pageBean = new PageBean_admin(totalBooks, page, pageSize, 10);

        model.addAttribute("bookList", bookList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBean.getPageCnt());
        model.addAttribute("filter", filter);
        model.addAttribute("pageBean", pageBean);
        
        String paramString = Stream.of(
                "keyword=" + keyword,
                "book_name=" + bookName,
                "author=" + author,
                "book_category=" + bookCategory,
                "publisher=" + publisher,
                "minPrice=" + minPrice,
                "maxPrice=" + maxPrice,
                "sortField=" + sortField,
                "sortOrder=" + sortOrder
        ).filter(param -> param != null && !param.endsWith("null"))
         .collect(Collectors.joining("&"));

        model.addAttribute("paramString", paramString);

        return "search/results";
    }
}
