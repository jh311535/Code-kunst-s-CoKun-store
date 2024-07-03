package controller;

import dto.UserCommentDTO_admin;
import dto.PageBean_admin;
import filter.UserCommentFilterDTO_admin;
import service.UserCommentService_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/admin/userComment")
public class UserCommentController_admin {

    @Autowired
    private UserCommentService_admin userCommentService;

    @GetMapping("/manage")
    public String manageComments(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "search", required = false) String search,
                                 @RequestParam(value = "sort", required = false) String sort,
                                 @RequestParam(value = "pageChange", required = false) String pageChange,
                                 Model model,
                                 HttpSession session) {
        UserCommentFilterDTO_admin filter = (UserCommentFilterDTO_admin) session.getAttribute("userCommentFilter");
        if (filter == null || (search == null && sort == null && pageChange == null)) {
            filter = new UserCommentFilterDTO_admin();
            session.setAttribute("userCommentFilter", filter);
        }

        int pageSize = 10;
        int paginationCnt = 10;
        int offset = (page - 1) * pageSize;
        filter.setOffset(offset);
        filter.setPageSize(pageSize);

        List<UserCommentDTO_admin> commentList = userCommentService.getCommentsByFilterAndSort(filter);
        int totalComments = userCommentService.getCommentCountByFilter(filter);
        PageBean_admin pageBean = new PageBean_admin(totalComments, page, pageSize, paginationCnt);

        // 날짜 형식 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        for (UserCommentDTO_admin comment : commentList) {
            try {
                comment.setComment_date(outputFormat.format(inputFormat.parse(comment.getComment_date().toString())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("commentList", commentList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBean.getPageCnt());
        model.addAttribute("filter", filter);
        model.addAttribute("pageBean", pageBean);

        return "admin/userComment/manage";
    }

    @PostMapping("/search")
    public String searchComments(
            @RequestParam(required = false) Integer comment_id,
            @RequestParam(required = false) Integer board_id,
            @RequestParam(required = false) Integer user_idx,
            @RequestParam(required = false) String comment_content,
            @RequestParam(required = false) String start_date,
            @RequestParam(required = false) String end_date,
            @RequestParam(required = false) String board_title,
            @RequestParam String returnJSP,
            @ModelAttribute UserCommentFilterDTO_admin filter,
            HttpSession session) {

        filter.setComment_id(comment_id);
        filter.setBoard_id(board_id);
        filter.setUser_idx(user_idx);
        filter.setComment_content(comment_content);
        filter.setStart_date(start_date);
        filter.setEnd_date(end_date);
        filter.setBoard_title(board_title);

        session.setAttribute("userCommentFilter", filter);

        return "redirect:/admin/userComment/manage?page=1&search=true";
    }

    @GetMapping("/sort")
    public String sortComments(@RequestParam("sortField") String sortField,
                               @RequestParam String returnJSP, HttpSession session) {
        UserCommentFilterDTO_admin filter = (UserCommentFilterDTO_admin) session.getAttribute("userCommentFilter");

        if (filter != null) {
            switch (sortField) {
                case "commentId":
                    filter.setCommentIdOrder(toggleOrder(filter.getCommentIdOrder()));
                    break;
                case "boardId":
                    filter.setBoardIdOrder(toggleOrder(filter.getBoardIdOrder()));
                    break;
                case "userIdx":
                    filter.setUserIdxOrder(toggleOrder(filter.getUserIdxOrder()));
                    break;
                case "content":
                    filter.setContentOrder(toggleOrder(filter.getContentOrder()));
                    break;
                case "date":
                    filter.setDateOrder(toggleOrder(filter.getDateOrder()));
                    break;
                case "title":
                    filter.setTitleOrder(toggleOrder(filter.getTitleOrder()));
                    break;
                default:
                    break;
            }
            session.setAttribute("userCommentFilter", filter);
        }

        return "redirect:/admin/userComment/manage?page=1&sort=true";
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

    @GetMapping("/detail/{id}")
    public ModelAndView detailComment(@PathVariable("id") int id) {
        UserCommentDTO_admin comment = userCommentService.getCommentWithBoardTitleById(id);

        // 날짜 형식 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        try {
            comment.setComment_date(outputFormat.format(inputFormat.parse(comment.getComment_date().toString())));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ModelAndView mav = new ModelAndView("admin/userComment/detail");
        mav.addObject("comment", comment);
        return mav;
    }

    @GetMapping("/modify/{id}")
    public ModelAndView modifyCommentForm(@PathVariable("id") int id) {
        UserCommentDTO_admin comment = userCommentService.getCommentById(id);

        // 날짜 형식 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        try {
            comment.setComment_date(outputFormat.format(inputFormat.parse(comment.getComment_date().toString())));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ModelAndView mav = new ModelAndView("admin/userComment/modify");
        mav.addObject("comment", comment);
        return mav;
    }

    @PostMapping("/modify")
    public String modifyComment(@ModelAttribute UserCommentDTO_admin comment) {
        userCommentService.updateComment(comment);
        return "redirect:/admin/userComment/detail/" + comment.getComment_id();
    }

    @PostMapping("/delete")
    public String deleteComment(@RequestParam("id") int id) {
        userCommentService.deleteComment(id);
        return "redirect:/admin/userComment/manage";
    }
}
