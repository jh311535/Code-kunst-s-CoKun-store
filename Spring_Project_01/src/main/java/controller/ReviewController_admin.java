package controller;

import dto.ReviewDTO_admin;
import dto.PageBean_admin;
import filter.ReviewFilterDTO_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.ReviewService_admin;
import service.UserInfoService_admin;
import service.BookService_admin;

import javax.servlet.http.HttpSession;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/admin/review")
public class ReviewController_admin {

    @Autowired
    private ReviewService_admin reviewService;

    @Autowired
    private BookService_admin bookService;

    @Autowired
    private UserInfoService_admin userInfoService;

    @GetMapping("/manage")
    public String manageReviews(@RequestParam(value = "page", defaultValue = "1") int page,
                                @RequestParam(value = "search", required = false) String search,
                                @RequestParam(value = "sort", required = false) String sort,
                                @RequestParam(value = "pageChange", required = false) String pageChange,
                                Model model,
                                HttpSession session) {
        ReviewFilterDTO_admin filter = (ReviewFilterDTO_admin) session.getAttribute("reviewFilter");
        if (filter == null || (search == null && sort == null && pageChange == null)) {
            filter = new ReviewFilterDTO_admin();
            session.setAttribute("reviewFilter", filter);
        }

        int pageSize = 10;
        int paginationCnt = 10;
        int offset = (page - 1) * pageSize;
        filter.setOffset(offset);
        filter.setPageSize(pageSize);

        List<ReviewDTO_admin> reviewList = reviewService.getReviewsByFilterAndSort(filter);
        int totalReviews = reviewService.getReviewCountByFilter(filter);  // 총 리뷰 수를 계산합니다.
        PageBean_admin pageBean = new PageBean_admin(totalReviews, page, pageSize, paginationCnt);

        // 책 이름과 책 이미지도 넣기
        for (ReviewDTO_admin review : reviewList) {
            int temp_book_id = review.getBook_id();
            String bookName = bookService.getBookNameById(temp_book_id);
            String bookPic = bookService.getBookPicById(temp_book_id);
            review.setBook_name(bookName);
            review.setBook_pic(bookPic);

            // 날짜 형식 변환
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
            try {
                review.setReview_date(outputFormat.format(inputFormat.parse(review.getReview_date())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("reviewList", reviewList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBean.getPageCnt());
        model.addAttribute("filter", filter);
        model.addAttribute("pageBean", pageBean);

        return "admin/review/manage";
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detailReview(@PathVariable("id") int id) {
        ReviewDTO_admin review = reviewService.getReviewById(id);

        // 책 이름과 사진 URL
        String bookName = bookService.getBookNameById(review.getBook_id());
        String bookPic = bookService.getBookPicById(review.getBook_id());

        // 작성자 닉네임
        String userNickname = userInfoService.getUserById(review.getUser_idx()).getUser_nickname();
        
        // 날짜 형식 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        try {
            review.setReview_date(outputFormat.format(inputFormat.parse(review.getReview_date())));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ModelAndView mav = new ModelAndView("admin/review/detail");
        mav.addObject("review", review);
        mav.addObject("bookName", bookName);
        mav.addObject("bookPic", bookPic);
        mav.addObject("userNickname", userNickname);
        return mav;
    }

    @PostMapping("/delete")
    public String deleteReview(@RequestParam("id") int id) {
        reviewService.deleteReview(id);
        return "redirect:/admin/review/manage";
    }

    @GetMapping("/modify/{id}")
    public ModelAndView modifyReviewForm(@PathVariable("id") int id) {
        ReviewDTO_admin review = reviewService.getReviewById(id);
        
        // 책 이름과 사진 URL
        String bookName = bookService.getBookNameById(review.getBook_id());
        String bookPic = bookService.getBookPicById(review.getBook_id());

        // 작성자 닉네임
        String userNickname = userInfoService.getUserById(review.getUser_idx()).getUser_nickname();
        
        ModelAndView mav = new ModelAndView("admin/review/modify");
        mav.addObject("review", review);
        mav.addObject("bookName", bookName);
        mav.addObject("bookPic", bookPic);
        mav.addObject("userNickname", userNickname);
        return mav;
    }

    @PostMapping("/modify")
    public String modifyReview(@ModelAttribute ReviewDTO_admin review) {
        reviewService.updateReview(review);
        //return "redirect:/admin/review/manage?page=1";
        
        // 수정 완료 후 상세 페이지로 리다이렉트
        return "redirect:/admin/review/detail/" + review.getReview_id();
    }

    @PostMapping("/search")
    public String searchReviews(
            @RequestParam(required = false) Integer review_id,
            @RequestParam(required = false) Integer user_idx,
            @RequestParam(required = false) Integer book_id,
            @RequestParam(required = false) Double rating_min,
            @RequestParam(required = false) Double rating_max,
            @RequestParam(required = false) String review_content,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam String returnJSP,
            @ModelAttribute ReviewFilterDTO_admin filter,
            HttpSession session) {

        filter.setReview_id(review_id);
        filter.setUser_idx(user_idx);
        filter.setBook_id(book_id);
        filter.setRating_min(rating_min);
        filter.setRating_max(rating_max);
        filter.setReview_content(review_content);
        filter.setStartDate(startDate);
        filter.setEndDate(endDate);

        session.setAttribute("reviewFilter", filter);

        return "redirect:/admin/review/manage?page=1&search=true";
    }

    @GetMapping("/sort")
    public String sortReviews(@RequestParam("sortField") String sortField,
                              @RequestParam String returnJSP, HttpSession session) {
        ReviewFilterDTO_admin filter = (ReviewFilterDTO_admin) session.getAttribute("reviewFilter");

        if (filter != null) {
            switch (sortField) {
                case "reviewId":
                    filter.setReviewIdOrder(toggleOrder(filter.getReviewIdOrder()));
                    break;
                case "userIdx":
                    filter.setUserIdxOrder(toggleOrder(filter.getUserIdxOrder()));
                    break;
                case "bookId":
                    filter.setBookIdOrder(toggleOrder(filter.getBookIdOrder()));
                    break;
                case "rating":
                    filter.setRatingOrder(toggleOrder(filter.getRatingOrder()));
                    break;
                case "reviewDate":
                    filter.setReviewDateOrder(toggleOrder(filter.getReviewDateOrder()));
                    break;
                default:
                    break;
            }
            session.setAttribute("reviewFilter", filter);
        }

        return "redirect:/admin/review/manage?page=1&sort=true";
    }

    private String toggleOrder(String currentOrder) {
        if (currentOrder == null) {
            return "asc";
        } else if ("asc".equals(currentOrder)) {
            return "desc";
        } else {
            return null;
        }
    }
}
