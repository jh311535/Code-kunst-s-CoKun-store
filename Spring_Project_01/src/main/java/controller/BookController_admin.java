package controller;

import dto.BoardDTO_admin;
import dto.BookDTO_admin;
import dto.PageBean_admin;
import filter.BookFilterDTO_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.BookService_admin;

import javax.servlet.http.HttpSession;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/admin/book")
public class BookController_admin {

    @Autowired
    private BookService_admin bookService;

    @GetMapping("/manage")
    public String manageBooks(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "search", required = false) String search,
                              @RequestParam(value = "sort", required = false) String sort,
                              @RequestParam(value = "pageChange", required = false) String pageChange,
                              Model model,
                              HttpSession session) {
        System.out.println("manageBooks 메서드 진입");
        BookFilterDTO_admin filter = (BookFilterDTO_admin) session.getAttribute("bookFilter");
        if (filter == null || (search == null && sort == null && pageChange == null)) {
            filter = new BookFilterDTO_admin();
            session.setAttribute("bookFilter", filter);
        }

        int pageSize = 10;
        int paginationCnt = 10;
        int offset = (page - 1) * pageSize;
        filter.setOffset(offset);
        filter.setPageSize(pageSize);

        List<BookDTO_admin> bookList = bookService.getBooksByFilterAndSort(filter);
        int totalBooks = bookService.getBookCountByFilter(filter);  // 총 책 수를 계산합니다.
        PageBean_admin pageBean = new PageBean_admin(totalBooks, page, pageSize, paginationCnt);


        // 날짜 형식 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        for (BookDTO_admin book : bookList) {
            try {
            	book.setFormattedDate(outputFormat.format(inputFormat.parse(book.getPublish_date().toString())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        
        model.addAttribute("bookList", bookList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBean.getPageCnt());
        model.addAttribute("filter", filter);
        model.addAttribute("pageBean", pageBean);
        System.out.println("manageBooks 메서드 종료");
        return "admin/book/manage";
    }

    @GetMapping("/foreign_key")
    public String foreignKeyBooks(@RequestParam(value = "page", defaultValue = "1") int page,
                                  @RequestParam(value = "search", required = false) String search,
                                  @RequestParam(value = "sort", required = false) String sort,
                                  @RequestParam(value = "pageChange", required = false) String pageChange,
                                  Model model,
                                  HttpSession session) {
        System.out.println("foreignKeyBooks 메서드 진입");
        BookFilterDTO_admin filter = (BookFilterDTO_admin) session.getAttribute("bookFilter");
        if (filter == null || (search == null && sort == null && pageChange == null)) {
            filter = new BookFilterDTO_admin();
            session.setAttribute("bookFilter", filter);
        }

        int pageSize = 10;
        int paginationCnt = 10;
        int offset = (page - 1) * pageSize;
        filter.setOffset(offset);
        filter.setPageSize(pageSize);

        List<BookDTO_admin> bookList = bookService.getBooksByFilterAndSort(filter);
        int totalBooks = bookService.getBookCountByFilter(filter);  // 총 책 수를 계산합니다.
        PageBean_admin pageBean = new PageBean_admin(totalBooks, page, pageSize, paginationCnt);

        model.addAttribute("bookList", bookList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBean.getPageCnt());
        model.addAttribute("filter", filter);
        model.addAttribute("pageBean", pageBean);
        System.out.println("foreignKeyBooks 메서드 종료");
        return "admin/book/foreign_key";
    }

    @PostMapping("/search")
    public String searchBooks(
            @RequestParam(required = false) Integer book_id,
            @RequestParam(required = false) String book_name,
            @RequestParam(required = false) Long isbn,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) String book_category,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer minInventory,
            @RequestParam(required = false) Integer maxInventory,
            @RequestParam String returnJSP,
            @ModelAttribute BookFilterDTO_admin filter,
            HttpSession session) {
        System.out.println("searchBooks 메서드 진입");

        filter.setBook_id(book_id);
        filter.setBook_name(book_name);
        filter.setIsbn(isbn);
        filter.setPublisher(publisher);
        filter.setBook_category(book_category);
        filter.setAuthor(author);
        filter.setStartDate(startDate);
        filter.setEndDate(endDate);
        filter.setMinPrice(minPrice);
        filter.setMaxPrice(maxPrice);
        filter.setMinInventory(minInventory);
        filter.setMaxInventory(maxInventory);

        session.setAttribute("bookFilter", filter);
        System.out.println("searchBooks 메서드 종료");

        if(returnJSP.equals("manageJSP")) {
            return "redirect:/admin/book/manage?page=1&search=true";
        } else if (returnJSP.equals("foreignJSP")) {
            return "redirect:/admin/book/foreign_key?page=1&search=true";
        } else {
            System.out.println("컨트롤러의 searchBooks 메서드에서 오류 발생");
            return "admin/book/manage";
        }
    }

    @GetMapping("/sort")
    public String sortBooks(@RequestParam("sortField") String sortField,
                            @RequestParam String returnJSP, HttpSession session) {
        System.out.println("sortBooks 메서드 진입, sortField: " + sortField);
        BookFilterDTO_admin filter = (BookFilterDTO_admin) session.getAttribute("bookFilter");

        if (filter != null) {
            switch (sortField) {
                case "bookId":
                    filter.setBookIdOrder(toggleOrder(filter.getBookIdOrder()));
                    break;
                case "bookName":
                    filter.setBookNameOrder(toggleOrder(filter.getBookNameOrder()));
                    break;
                case "isbn":
                    filter.setIsbnOrder(toggleOrder(filter.getIsbnOrder()));
                    break;
                case "price":
                    filter.setPriceOrder(toggleOrder(filter.getPriceOrder()));
                    break;
                case "publishDate":
                    filter.setPublishDateOrder(toggleOrder(filter.getPublishDateOrder()));
                    break;
                case "publisher":
                    filter.setPublisherOrder(toggleOrder(filter.getPublisherOrder()));
                    break;
                case "category":
                    filter.setCategoryOrder(toggleOrder(filter.getCategoryOrder()));
                    break;
                case "author":
                    filter.setAuthorOrder(toggleOrder(filter.getAuthorOrder()));
                    break;
                case "inventory":
                    filter.setInventoryOrder(toggleOrder(filter.getInventoryOrder()));
                    break;
                default:
                    break;
            }
            session.setAttribute("bookFilter", filter);
        }
        System.out.println("sortBooks 메서드 종료");

        if(returnJSP.equals("manageJSP")) {
            return "redirect:/admin/book/manage?page=1&sort=true";
        } else if (returnJSP.equals("foreignJSP")) {
            return "redirect:/admin/book/foreign_key?page=1&sort=true";
        } else {
            System.out.println("컨트롤러의 sortBooks 메서드에서 오류 발생");
            return "admin/book/manage";
        }
    }
    
    private String toggleOrder(String currentOrder) {
        System.out.println("정렬 순서 토글: " + currentOrder);
        if (currentOrder == null) {
            return "asc";
        } else if ("asc".equals(currentOrder)) {
            return "desc";
        } else {
            return null;
        }
    }
    
    // 책 추가
    @GetMapping("/add")
    public String addBookForm() {
        return "admin/book/add";
    }

    // 책 추가 처리
    @PostMapping("/add")
    public String addBook(@ModelAttribute BookDTO_admin book) {
        bookService.insertBook(book);
        return "redirect:/admin/book/manage";
    }


    @GetMapping("/modify/{id}")
    public ModelAndView modifyBookForm(@PathVariable("id") int id) {
        System.out.println("modifyBookForm 메서드 진입");
        BookDTO_admin book = bookService.getBookById(id);
        
        // 날짜 형식 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        try {
        	book.setFormattedDate(outputFormat.format(inputFormat.parse(book.getPublish_date().toString())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        ModelAndView mav = new ModelAndView("admin/book/modify");
        mav.addObject("book", book);
        System.out.println("modifyBookForm 메서드 종료");
        return mav;
    }

    @PostMapping("/modify")
    public String modifyBook(@ModelAttribute BookDTO_admin book) {
        System.out.println("modifyBook 메서드 진입");
        bookService.updateBook(book);
        System.out.println("modifyBook 메서드 종료");
        // return "redirect:/admin/book/manage?page=1";
        
        // 수정 완료 후 상세 페이지로 리다이렉트
        return "redirect:/admin/book/detail/" + book.getBook_id();
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detailBook(@PathVariable("id") int id) {
        System.out.println("detailBook 메서드 진입");
        BookDTO_admin book = bookService.getBookById(id);
        
        // 날짜 형식 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        try {
        	book.setFormattedDate(outputFormat.format(inputFormat.parse(book.getPublish_date().toString())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        ModelAndView mav = new ModelAndView("admin/book/detail");
        mav.addObject("book", book);
        System.out.println("detailBook 메서드 종료");
        return mav;
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam("id") int id) {
        System.out.println("deleteBook 메서드 진입");
        bookService.deleteBook(id);
        System.out.println("deleteBook 메서드 종료");
        return "redirect:/admin/book/manage?page=1";
    }
}
