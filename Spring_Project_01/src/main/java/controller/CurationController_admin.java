package controller;

import dto.CurationDTO_admin;
import dto.PageBean_admin;
import filter.CurationFilterDTO_admin;
import service.CurationService_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/curation")
public class CurationController_admin {

    @Autowired
    private CurationService_admin curationService;

    @GetMapping("/manage")
    public String manageCurations(@RequestParam(value = "page", defaultValue = "1") int page,
                                  @RequestParam(value = "search", required = false) String search,
                                  @RequestParam(value = "sort", required = false) String sort,
                                  @RequestParam(value = "pageChange", required = false) String pageChange,
                                  Model model,
                                  HttpSession session) {
        CurationFilterDTO_admin filter = (CurationFilterDTO_admin) session.getAttribute("curationFilter");
        if (filter == null || (search == null && sort == null && pageChange == null)) {
            filter = new CurationFilterDTO_admin();
            session.setAttribute("curationFilter", filter);
        }

        int pageSize = 10;
        int paginationCnt = 10;
        int offset = (page - 1) * pageSize;
        filter.setOffset(offset);
        filter.setPageSize(pageSize);

        List<CurationDTO_admin> curationList = curationService.getCurationsByFilterAndSort(filter);
        int totalCurations = curationService.getCurationCountByFilter(filter);
        PageBean_admin pageBean = new PageBean_admin(totalCurations, page, pageSize, paginationCnt);

        model.addAttribute("curationList", curationList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBean.getPageCnt());
        model.addAttribute("filter", filter);
        model.addAttribute("pageBean", pageBean);

        return "admin/curation/manage";
    }

    @GetMapping("/foreign_key")
    public String foreignKeyCurations(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "search", required = false) String search,
                                      @RequestParam(value = "sort", required = false) String sort,
                                      @RequestParam(value = "pageChange", required = false) String pageChange,
                                      Model model,
                                      HttpSession session) {
        CurationFilterDTO_admin filter = (CurationFilterDTO_admin) session.getAttribute("curationFilter");
        if (filter == null || (search == null && sort == null && pageChange == null)) {
            filter = new CurationFilterDTO_admin();
            session.setAttribute("curationFilter", filter);
        }

        int pageSize = 10;
        int paginationCnt = 10;
        int offset = (page - 1) * pageSize;
        filter.setOffset(offset);
        filter.setPageSize(pageSize);

        List<CurationDTO_admin> curationList = curationService.getCurationsByFilterAndSort(filter);
        int totalCurations = curationService.getCurationCountByFilter(filter);
        PageBean_admin pageBean = new PageBean_admin(totalCurations, page, pageSize, paginationCnt);

        model.addAttribute("curationList", curationList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBean.getPageCnt());
        model.addAttribute("filter", filter);
        model.addAttribute("pageBean", pageBean);

        return "admin/curation/foreign_key";
    }

    @PostMapping("/search")
    public String searchCurations(
            @RequestParam(required = false) Integer curation_id,
            @RequestParam(required = false) String curation_category,
            @RequestParam(required = false) String product_name,
            @RequestParam String returnJSP,
            @ModelAttribute CurationFilterDTO_admin filter,
            HttpSession session) {

        filter.setCuration_id(curation_id);
        filter.setCuration_category(curation_category);
        filter.setProduct_name(product_name);

        session.setAttribute("curationFilter", filter);

        if (returnJSP.equals("manageJSP")) {
            return "redirect:/admin/curation/manage?page=1&search=true";
        } else if (returnJSP.equals("foreignJSP")) {
            return "redirect:/admin/curation/foreign_key?page=1&search=true";
        } else {
            return "admin/curation/manage";
        }
    }

    @GetMapping("/sort")
    public String sortCurations(@RequestParam("sortField") String sortField,
                                @RequestParam String returnJSP, HttpSession session) {
        CurationFilterDTO_admin filter = (CurationFilterDTO_admin) session.getAttribute("curationFilter");

        if (filter != null) {
            switch (sortField) {
                case "curationId":
                    filter.setCurationIdOrder(toggleOrder(filter.getCurationIdOrder()));
                    break;
                case "curationCategory":
                    filter.setCurationCategoryOrder(toggleOrder(filter.getCurationCategoryOrder()));
                    break;
                case "productName":
                    filter.setProductNameOrder(toggleOrder(filter.getProductNameOrder()));
                    break;
                case "productUrl":
                    filter.setProductUrlOrder(toggleOrder(filter.getProductUrlOrder()));
                    break;
                default:
                    break;
            }
            session.setAttribute("curationFilter", filter);
        }

        if (returnJSP.equals("manageJSP")) {
            return "redirect:/admin/curation/manage?page=1&sort=true";
        } else if (returnJSP.equals("foreignJSP")) {
            return "redirect:/admin/curation/foreign_key?page=1&sort=true";
        } else {
            return "admin/curation/manage";
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

    // 큐레이션 추가
    @GetMapping("/add")
    public String addCurationForm() {
        return "admin/curation/add";
    }

    // 큐레이션 추가 처리
    @PostMapping("/add")
    public String addCuration(@ModelAttribute CurationDTO_admin curation) {
        curationService.insertCuration(curation);
        return "redirect:/admin/curation/manage";
    }
    
    @GetMapping("/modify/{id}")
    public ModelAndView modifyCurationForm(@PathVariable("id") int id) {
        CurationDTO_admin curation = curationService.getCurationById(id);
        ModelAndView mav = new ModelAndView("admin/curation/modify");
        mav.addObject("curation", curation);
        return mav;
    }

    @PostMapping("/modify")
    public String modifyCuration(@ModelAttribute CurationDTO_admin curation) {
        curationService.updateCuration(curation);
        // return "redirect:/admin/curation/manage?page=1";
        
        // 수정 완료 후 상세 페이지로 리다이렉트
        return "redirect:/admin/curation/detail/" + curation.getCuration_id();
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detailCuration(@PathVariable("id") int id) {
        CurationDTO_admin curation = curationService.getCurationById(id);
        ModelAndView mav = new ModelAndView("admin/curation/detail");
        mav.addObject("curation", curation);
        return mav;
    }

    @PostMapping("/delete")
    public String deleteCuration(@RequestParam("id") int id) {
        curationService.deleteCuration(id);
        return "redirect:/admin/curation/manage";
    }
}
