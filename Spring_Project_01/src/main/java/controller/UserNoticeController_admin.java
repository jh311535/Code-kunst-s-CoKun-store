package controller;

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
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.text.DateFormatSymbols;

@Controller
@RequestMapping("/notice")
public class UserNoticeController_admin {

    @Autowired
    private NoticeService_admin noticeService;

    @Autowired
    private AdminInfoService_admin adminInfoService;

    @GetMapping("/list")
    public String listNotices(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "searchType", required = false) String searchType,
                              @RequestParam(value = "query", required = false) String query,
                              @RequestParam(value = "sort", required = false) String sort,
                              @RequestParam(value = "pageChange", required = false) String pageChange,
                              @RequestParam(value = "sortChange", required = false) String sortChange,
                              Model model,
                              HttpSession session) {
        NoticeFilterDTO_admin filter = (NoticeFilterDTO_admin) session.getAttribute("noticeFilter");
        if (filter == null || (pageChange == null && sortChange == null)) {
            filter = new NoticeFilterDTO_admin();
            session.setAttribute("noticeFilter", filter);
        }

        int pageSize = 10;
        int paginationCnt = 10;
        int offset = (page - 1) * pageSize;
        filter.setOffset(offset);
        filter.setPageSize(pageSize);

        if (sortChange != null) {
            if ("date_desc".equals(sort)) {
                if ("desc".equals(filter.getDateOrder())) {
                    filter.setDateOrder(null); // 정렬 해제
                } else {
                    filter.setDateOrder("desc");
                }
            } else if ("date_asc".equals(sort)) {
                if ("asc".equals(filter.getDateOrder())) {
                    filter.setDateOrder(null); // 정렬 해제
                } else {
                    filter.setDateOrder("asc");
                }
            } else {
                filter.setDateOrder(null);
            }
        }

        if (searchType != null && query != null) {
            if ("title".equals(searchType)) {
                filter.setNotice_title(query);
                filter.setNotice_content(null);
            } else if ("content".equals(searchType)) {
                filter.setNotice_content(query);
                filter.setNotice_title(null);
            }
        }

        List<NoticeDTO_admin> noticeList = noticeService.getNoticesByFilterAndSort(filter);
        int totalNotices = noticeService.getNoticeCountByFilter(filter);
        PageBean_admin pageBean = new PageBean_admin(totalNotices, page, pageSize, paginationCnt);

        // Add admin nicknames and format date
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        for (NoticeDTO_admin notice : noticeList) {
            String adminNickname = adminInfoService.getAdminById(notice.getAdmin_idx()).getAdmin_nickname();
            notice.setAdmin_nickname(adminNickname);

            try {
                notice.setFormattedDate(outputFormat.format(inputFormat.parse(notice.getNotice_date())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBean.getPageCnt());
        model.addAttribute("filter", filter);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("sort", sort);

        return "notice/list";
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detailNotice(@PathVariable("id") int id) {
        noticeService.incrementViews(id);  // 조회수 증가
        NoticeDTO_admin notice = noticeService.getNoticeById(id);
        String adminNickname = adminInfoService.getAdminById(notice.getAdmin_idx()).getAdmin_nickname();

        // Format the date
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd (E)", new DateFormatSymbols(Locale.KOREAN));
        try {
            notice.setFormattedDate(outputFormat.format(inputFormat.parse(notice.getNotice_date())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView mav = new ModelAndView("notice/detail");
        mav.addObject("notice", notice);
        mav.addObject("adminNickname", adminNickname);
        return mav;
    }
}
