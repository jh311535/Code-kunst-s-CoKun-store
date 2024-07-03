package kr.co.soldesk.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.soldesk.beans.ReviewBean;
import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.service.BookService;
import kr.co.soldesk.service.ReviewService;
import kr.co.soldesk.service.SNSService;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;
    
    @GetMapping("/main")
    public String myMain(Model model,HttpSession session
                   ,@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        model.addAttribute("bookList", bookService.getBookList());
        // 여기 메뉴바영역 : 필요한 경우 다른 리스트를 추가
        model.addAttribute("soccer", bookService.getBookList()); // 메뉴바 1번항목
        model.addAttribute("newReleases", bookService.getBookList()); //  메뉴바 2번항목
        model.addAttribute("steadySellers", bookService.getBookList()); //  메뉴바 3번항목
        model.addAttribute("comics", bookService.getBookList()); //  메뉴바 4번항목
        
        UserBean loginUserBean=(UserBean)session.getAttribute("loginUserBean");
        int a;
        if(loginUserBean!=null) {
        List<ReviewBean> reviews=reviewService.getFollowReview(loginUserBean.getUser_idx(), pageNumber);
        if(reviews.isEmpty()) {
           reviews=reviewService.getAllReview(pageNumber);
        }
        model.addAttribute("userReview",reviews);
        model.addAttribute("loginUserBean",loginUserBean);
           
        a=1;
        }else {
           List<ReviewBean> reviews=reviewService.getAllReview(pageNumber);
           model.addAttribute("userReview",reviews);
           a=2;
        }
        model.addAttribute("a",a);
        return "main";
        
    } 
    
} 



/*
*
*
*
*
*
*
package kr.co.soldesk.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.soldesk.beans.ReviewBean;
import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.service.BookService;
import kr.co.soldesk.service.ReviewService;
import kr.co.soldesk.service.SNSService;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;
    
    @GetMapping("/main")
    public String myMain(Model model,HttpSession session
    					,@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        model.addAttribute("bookList", bookService.getBookList());
        // 여기 메뉴바영역 : 필요한 경우 다른 리스트를 추가
        model.addAttribute("soccer", bookService.getBookList()); // 메뉴바 1번항목
        model.addAttribute("newReleases", bookService.getBookList()); //  메뉴바 2번항목
        model.addAttribute("steadySellers", bookService.getBookList()); //  메뉴바 3번항목
        model.addAttribute("comics", bookService.getBookList()); //  메뉴바 4번항목
        
        UserBean loginUserBean=(UserBean)session.getAttribute("loginUserBean");
        int a;
        if(loginUserBean!=null) {
        List<ReviewBean> reviews=reviewService.getFollowReview(loginUserBean.getUser_idx(), pageNumber);
        model.addAttribute("userReview",reviews);
        model.addAttribute("loginUserBean",loginUserBean);
        a=1;
        }else {
        	List<ReviewBean> reviews=reviewService.getAllReview(pageNumber);
        	model.addAttribute("userReview",reviews);
        	a=2;
        }
        model.addAttribute("a",a);
        return "main";
        
    } 
    
}
*
*
*
*
*
*/