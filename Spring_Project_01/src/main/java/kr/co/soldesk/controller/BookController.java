package kr.co.soldesk.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.soldesk.beans.BookDTO;
import kr.co.soldesk.beans.NaverDTO;
import kr.co.soldesk.beans.PageBean;
import kr.co.soldesk.beans.ReviewBean;
import kr.co.soldesk.beans.SidebarDTO;
import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.beans.YouTubeDto;
import kr.co.soldesk.service.BookService;
import kr.co.soldesk.service.NaverService;
import kr.co.soldesk.service.ReviewService;
import kr.co.soldesk.service.SidebarService;
import kr.co.soldesk.service.UserService;
import kr.co.soldesk.youtube.YouTubeSearch;

@Controller
@RequestMapping("/books")
public class BookController {
	// 책API
	@Autowired
	private BookService bookService;
	// 유튜브API
	@Autowired
	private YouTubeSearch youTubeSearch;

	@Autowired
    private ReviewService reviewService;

	
	@Autowired
	private SidebarService sidebarService;

	@Autowired
	private NaverService naverService;
	
	@Autowired
	private UserService userService;

	 @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
	        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	    }
	 
	@GetMapping("/bookDetail")
	public String bookDetail(@RequestParam("book_id") int book_id, Model model
			 ,@RequestParam(value = "page", defaultValue = "1") int pageNumber,
             @ModelAttribute("reviewBean") ReviewBean reviewBean,
             HttpSession session) {
		
		List<BookDTO> bookList = bookService.getBookList();
		model.addAttribute("bookList", bookList);

		
		/*------------밑에건 신경X ------------------*/
		 /*------------유튜브+큐레이팅 카테고리저장------------------*/
		String bookCategory = "";
		for (BookDTO book : bookList) {
			if (book.getBook_id() == book_id) {
				bookCategory = book.getBook_category();
				break;
			}
		}
		 /*------------유튜브------------------*/
		List<YouTubeDto> allVideos = youTubeSearch.searchVideos(bookCategory + " 추천");
		List<YouTubeDto> recommendedVideos = youTubeSearch.getRandomVideos(allVideos, 3);
		model.addAttribute("recommendedVideos", recommendedVideos);
		 /*------------큐레이팅------------------*/
		List<NaverDTO> allCuration = naverService.getNaverList(bookCategory);
		List<NaverDTO> recommendedCuration = naverService.getRandomCuration(allCuration, 3);

		// 필터링
		List<NaverDTO> filteredCuration = new ArrayList<>();
		for (NaverDTO curation : recommendedCuration) {
			for (BookDTO book : bookList) {
				if (curation.getCuration_category().equals(book.getBook_category())) {
					filteredCuration.add(curation);
					break;
				}
			}
		}
		model.addAttribute("filteredCuration", filteredCuration);
		
		 /*------------추천도서------------------*/
	    BookDTO selectedBook = null;
	    for (BookDTO book : bookList) {
	        if (book.getBook_id() == book_id) {
	            selectedBook = book;
	            break;
	        }
	    }

	    if (selectedBook != null) {
	        String selectedCategory = selectedBook.getBook_category();
	        List<BookDTO> filteredBooks = new ArrayList<>();
	        for (BookDTO book : bookList) {
	            if (book.getBook_category().equals(selectedCategory) && book.getBook_id() != selectedBook.getBook_id()) {
	                filteredBooks.add(book);
	            }
	        }
	        Collections.shuffle(filteredBooks);
	        List<BookDTO> randomBooks = filteredBooks.subList(0, Math.min(5, filteredBooks.size()));
	        model.addAttribute("randomBooks", randomBooks);
	    }
	   
	    /*------------상세페이지 하단부분끝------------------*/
        List<ReviewBean> reviews = reviewService.getReviewsForBook(book_id, pageNumber);
        int totalReviews = reviewService.countReviewsForBook(book_id);
        int totalPages = (int) Math.ceil((double) totalReviews / 3);
        model.addAttribute("reviews", reviews);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("book_id", book_id);

        UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");
        UserBean user = null;
        if (loginUserBean != null) {
            reviewBean.setBook_id(book_id);
            reviewBean.setUser_idx(loginUserBean.getUser_idx());
            model.addAttribute("book_id", book_id);
            user = userService.getUserInfo(loginUserBean);
            model.addAttribute("user", user);
            model.addAttribute("loginUserBean",loginUserBean);
        }
        
        /* 리뷰 부분 끝 */
		return "books/bookDetail";
	}

	@GetMapping("/page")
	public String page(@RequestParam("sidebar_id") int sidebar_id,
			@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		List<SidebarDTO> sidebarList = sidebarService.getSidebarList();
		model.addAttribute("sidebarList", sidebarList);

		SidebarDTO selectedCategory = null;
		for (SidebarDTO sidebar : sidebarList) {
			if (sidebar.getSidebar_id() == sidebar_id) {
				selectedCategory = sidebar;
				break;
			}
		}

		if (selectedCategory != null) {
			String category = selectedCategory.getSidebar_name();

			List<String> categories = new ArrayList<>();

			// 카테고리 매핑
			switch (category) {
			case "건강":
				categories.add("건강");
				categories.add("다이어트");
				break;
			case "스포츠":
				categories.add("축구");
				categories.add("농구");
				categories.add("야구");
				categories.add("수영");
				break;
			case "여행":
				categories.add("유럽여행");
				categories.add("일본여행");
				break;
			case "요리":
				categories.add("한국요리");
				categories.add("일본요리");
				categories.add("중국요리");
				categories.add("프랑스요리");
				categories.add("사찰요리");
				categories.add("생활요리");
				break;
			case "음악":
				categories.add("서양음악");
				categories.add("재즈");
				categories.add("팝/록");
				break;
			default:
				categories.add(category);
			}

			List<BookDTO> bookList = bookService.getBooksByCategories(categories);

			int contentCnt = bookList.size();
			int page_listcnt = bookService.getPageListCnt();
			int page_paginationcnt = bookService.getPagePaginationCnt();
			PageBean pageBean = new PageBean(contentCnt, page, page_listcnt, page_paginationcnt);

			int start = (page - 1) * page_listcnt;
			int end = Math.min(start + page_listcnt, contentCnt);

			List<BookDTO> paginatedBookList = bookList.subList(start, end);

			model.addAttribute("sidebar_name", category);
			model.addAttribute("bookList", paginatedBookList);
			model.addAttribute("pageBean", pageBean);
		}

		return "books/page";
	}

	@GetMapping("/bestseller")
	public String bestseller(Model model) {
		List<BookDTO> bestseller = bookService.getBookList();
		model.addAttribute("bestseller", bestseller);

		return "books/bestseller";
	}

}
