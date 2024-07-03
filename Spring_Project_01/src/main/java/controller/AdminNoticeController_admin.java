package controller;

import dto.AdminInfoDTO_admin;
import dto.NoticeDTO_admin;
import dto.PageBean_admin;
import filter.NoticeFilterDTO_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import service.AdminInfoService_admin;
import service.NoticeService_admin;

import javax.servlet.http.HttpSession;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/admin/notice")
public class AdminNoticeController_admin {

    @Autowired
    private NoticeService_admin noticeService;

    @Autowired
    private AdminInfoService_admin adminInfoService;

    @GetMapping("/manage")
    public String manageNotices(@RequestParam(value = "page", defaultValue = "1") int page,
                                @RequestParam(value = "search", required = false) String search,
                                @RequestParam(value = "sort", required = false) String sort,
                                @RequestParam(value = "pageChange", required = false) String pageChange,
                                Model model,
                                HttpSession session) {
        NoticeFilterDTO_admin filter = (NoticeFilterDTO_admin) session.getAttribute("noticeFilter");
        if (filter == null || (search == null && sort == null && pageChange == null)) {
            filter = new NoticeFilterDTO_admin();
            session.setAttribute("noticeFilter", filter);
        }

        int pageSize = 10;
        int paginationCnt = 10;
        int offset = (page - 1) * pageSize;
        filter.setOffset(offset);
        filter.setPageSize(pageSize);

        List<NoticeDTO_admin> noticeList = noticeService.getNoticesByFilterAndSort(filter);
        int totalNotices = noticeService.getNoticeCountByFilter(filter);
        PageBean_admin pageBean = new PageBean_admin(totalNotices, page, pageSize, paginationCnt);

        // 날짜 형식 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        for (NoticeDTO_admin notice : noticeList) {
            try {
                notice.setFormattedDate(outputFormat.format(inputFormat.parse(notice.getNotice_date())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBean.getPageCnt());
        model.addAttribute("filter", filter);
        model.addAttribute("pageBean", pageBean);

        return "admin/notice/manage";
    }

    @GetMapping("/foreign_key")
    public String foreignKeyNotices(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "search", required = false) String search,
                                    @RequestParam(value = "sort", required = false) String sort,
                                    @RequestParam(value = "pageChange", required = false) String pageChange,
                                    Model model,
                                    HttpSession session) {
        NoticeFilterDTO_admin filter = (NoticeFilterDTO_admin) session.getAttribute("noticeFilter");
        if (filter == null || (search == null && sort == null && pageChange == null)) {
            filter = new NoticeFilterDTO_admin();
            session.setAttribute("noticeFilter", filter);
        }

        int pageSize = 10;
        int paginationCnt = 10;
        int offset = (page - 1) * pageSize;
        filter.setOffset(offset);
        filter.setPageSize(pageSize);

        List<NoticeDTO_admin> noticeList = noticeService.getNoticesByFilterAndSort(filter);
        int totalNotices = noticeService.getNoticeCountByFilter(filter);
        PageBean_admin pageBean = new PageBean_admin(totalNotices, page, pageSize, paginationCnt);

        // 날짜 형식 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        for (NoticeDTO_admin notice : noticeList) {
            try {
                notice.setFormattedDate(outputFormat.format(inputFormat.parse(notice.getNotice_date())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBean.getPageCnt());
        model.addAttribute("filter", filter);
        model.addAttribute("pageBean", pageBean);

        return "admin/notice/foreign_key";
    }

    @PostMapping("/search")
    public String searchNotices(
            @RequestParam(required = false) Integer notice_id,
            @RequestParam(required = false) Integer admin_idx,
            @RequestParam(required = false) String notice_title,
            @RequestParam(required = false) String notice_content,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam String returnJSP,
            @ModelAttribute NoticeFilterDTO_admin filter,
            HttpSession session) {

        filter.setNotice_id(notice_id);
        filter.setAdmin_idx(admin_idx);
        filter.setNotice_title(notice_title);
        filter.setNotice_content(notice_content);
        filter.setStartDate(startDate);
        filter.setEndDate(endDate);

        session.setAttribute("noticeFilter", filter);

        if(returnJSP.equals("manageJSP")) {
            return "redirect:/admin/notice/manage?page=1&search=true";
        } else if (returnJSP.equals("foreignJSP")) {
            return "redirect:/admin/notice/foreign_key?page=1&search=true";
        } else {
            System.out.println("컨트롤러의 searchNotices 메서드에서 오류 발생");
            return "admin/notice/manage";
        }
    }

    @GetMapping("/sort")
    public String sortNotices(@RequestParam("sortField") String sortField,
                              @RequestParam String returnJSP, HttpSession session) {
        NoticeFilterDTO_admin filter = (NoticeFilterDTO_admin) session.getAttribute("noticeFilter");

        if (filter != null) {
            switch (sortField) {
                case "noticeId":
                    filter.setNoticeIdOrder(toggleOrder(filter.getNoticeIdOrder()));
                    break;
                case "noticeTitle":
                    filter.setTitleOrder(toggleOrder(filter.getTitleOrder()));
                    break;
                case "noticeContent":
                    filter.setContentOrder(toggleOrder(filter.getContentOrder()));
                    break;
                case "adminIdx":
                    filter.setAdminIdxOrder(toggleOrder(filter.getAdminIdxOrder()));
                    break;
                case "noticeDate":
                    filter.setDateOrder(toggleOrder(filter.getDateOrder()));
                    break;
                case "picOrder":
                    filter.setPicOrder(toggleOrder(filter.getPicOrder()));
                    break;
                case "viewsOrder":
                    filter.setViewsOrder(toggleOrder(filter.getViewsOrder()));
                    break;
                default:
                    break;
            }
            session.setAttribute("noticeFilter", filter);
        }

        if(returnJSP.equals("manageJSP")) {
            return "redirect:/admin/notice/manage?page=1&sort=true";
        } else if (returnJSP.equals("foreignJSP")) {
            return "redirect:/admin/notice/foreign_key?page=1&sort=true";
        } else {
            System.out.println("컨트롤러의 sortNotices 메서드에서 오류 발생");
            return "admin/notice/manage";
        }
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

    @GetMapping("/add")
    public String addNoticeForm(HttpSession session, Model model) {
        AdminInfoDTO_admin admin = (AdminInfoDTO_admin) session.getAttribute("admin");
        model.addAttribute("adminIdx", admin.getAdmin_idx());
        return "admin/notice/add";
    }

    @PostMapping("/add")
    public String addNotice(@ModelAttribute NoticeDTO_admin notice, HttpSession session) {
        AdminInfoDTO_admin admin = (AdminInfoDTO_admin) session.getAttribute("admin");
        notice.setAdmin_idx(admin.getAdmin_idx());
        noticeService.insertNotice(notice);
        return "redirect:/admin/notice/manage";
    }

    @GetMapping("/modify/{id}")
    public ModelAndView modifyNoticeForm(@PathVariable("id") int id, HttpSession session, Model model) {
        NoticeDTO_admin notice = noticeService.getNoticeById(id);
        AdminInfoDTO_admin admin = (AdminInfoDTO_admin) session.getAttribute("admin");
        model.addAttribute("adminIdx", admin.getAdmin_idx());
        ModelAndView mav = new ModelAndView("admin/notice/modify");
        mav.addObject("notice", notice);
        return mav;
    }

    @PostMapping("/modify")
    public String modifyNotice(@ModelAttribute NoticeDTO_admin notice,
    		@RequestParam(value = "delete_pic", defaultValue = "false") Boolean deletePic) {
        noticeService.updateNotice(notice, deletePic);
        
        // 수정 완료 후 상세 페이지로 리다이렉트
        return "redirect:/admin/notice/detail/" + notice.getNotice_id();
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detailNotice(@PathVariable("id") int id) {
        NoticeDTO_admin notice = noticeService.getNoticeById(id);
        
        // 작성자 닉네임
        String adminNickname = adminInfoService.getAdminById(notice.getAdmin_idx()).getAdmin_nickname();
        notice.setAdmin_nickname(adminNickname);

        // 날짜 형식 변환
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        try {
            notice.setFormattedDate(outputFormat.format(inputFormat.parse(notice.getNotice_date())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        ModelAndView mav = new ModelAndView("admin/notice/detail");
        mav.addObject("notice", notice);
        return mav;
    }

    @PostMapping("/delete")
    public String deleteNotice(@RequestParam("id") int id) {
        noticeService.deleteNotice(id);
        return "redirect:/admin/notice/manage";
    }
}
