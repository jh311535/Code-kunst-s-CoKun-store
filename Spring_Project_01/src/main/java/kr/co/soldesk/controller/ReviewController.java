package kr.co.soldesk.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import kr.co.soldesk.beans.ReviewBean;
import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/review_write")
    public String review_write(@ModelAttribute("reviewBean") ReviewBean reviewBean,
                               @RequestParam("book_id") int review_book_id,
                               HttpSession session,
                               Model model) {
        UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");
        if (loginUserBean == null) {
            return "user/not_login";
        } else {
            reviewBean.setBook_id(review_book_id);
            reviewBean.setUser_idx(loginUserBean.getUser_idx());
            model.addAttribute("book_id", review_book_id);
            return "review/review_write";
        }
    }

    @PostMapping("/review_write_pro")
    public String review_write_pro(@Valid @ModelAttribute("reviewBean") ReviewBean reviewBean,
                                   BindingResult result,
                                   HttpSession session) {
        UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");
        if (result.hasErrors()) {
            return "review/review_write";
        }
        Timestamp currentTimestamp = new Timestamp(new Date().getTime());
        reviewBean.setReview_date(currentTimestamp);
        reviewBean.setUser_idx(loginUserBean.getUser_idx());
        reviewService.addReview(reviewBean);
        return "review/review_write_success";
    }
    
    @PostMapping("/deleteReview")
    @ResponseBody
    public void deleteReview(@RequestBody ReviewBean reviewBean) {
            reviewService.deleteReview(reviewBean.getReview_id());

    }
    
    @GetMapping("/review_modify")
    public String modifyReview(@ModelAttribute("modifyReviewBean") ReviewBean modifyReviewBean
    						,Model model
    						,@RequestParam("review_id") int review_id) {
    		ReviewBean tempReviewBean=reviewService.getReviewContent(review_id);
    		
    		modifyReviewBean.setBook_id(tempReviewBean.getBook_id());
    		modifyReviewBean.setBookName(tempReviewBean.getBookName());
    		modifyReviewBean.setRating(tempReviewBean.getRating());
    		modifyReviewBean.setReview_content(tempReviewBean.getReview_content());
    		modifyReviewBean.setReview_date(tempReviewBean.getReview_date());
    		modifyReviewBean.setReview_id(review_id);
    		modifyReviewBean.setUser_idx(tempReviewBean.getUser_idx());
    		modifyReviewBean.setUserNickName(tempReviewBean.getUserNickName());
    		
    		
    	    model.addAttribute("modifyReviewBean", modifyReviewBean);
    	    
    		return "review/review_modify";
    }

    
	@PostMapping("modifyReview_pro")
	public String modify_pro(@Valid@ModelAttribute("modifyReviewBean") ReviewBean modifyReviewBean
			,BindingResult result
			,Model model){
		
		if(result.hasErrors()) {
			return "review/review_modify";
		}
		
		reviewService.modifyReview(modifyReviewBean);
		return "review/modifyReview_success";
		
	}
}