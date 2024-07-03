package controller;

import dto.AdminInfoDTO_admin;
import dto.PageBean_admin;
import filter.AdminInfoFilterDTO_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.AdminInfoService_admin;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/adminInfo")
public class AdminInfoController_admin {

    @Autowired
    private AdminInfoService_admin adminInfoService;

    @GetMapping("/manage")
    public String manageAdmins(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "search", required = false) String search,
                               @RequestParam(value = "sort", required = false) String sort,
                               @RequestParam(value = "pageChange", required = false) String pageChange,
                               Model model,
                               HttpSession session) {
        AdminInfoFilterDTO_admin filter = (AdminInfoFilterDTO_admin) session.getAttribute("adminInfoFilter");
        if (filter == null || (search == null && sort == null && pageChange == null)) {
            filter = new AdminInfoFilterDTO_admin();
            session.setAttribute("adminInfoFilter", filter);
        }

        int pageSize = 10;
        int paginationCnt = 10;
        int offset = (page - 1) * pageSize;
        filter.setOffset(offset);
        filter.setPageSize(pageSize);

        List<AdminInfoDTO_admin> adminList = adminInfoService.getAdminsByFilterAndSort(filter);
        int totalAdmins = adminInfoService.getAdminCountByFilter(filter);
        PageBean_admin pageBean = new PageBean_admin(totalAdmins, page, pageSize, paginationCnt);

        model.addAttribute("adminList", adminList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBean.getPageCnt());
        model.addAttribute("filter", filter);
        model.addAttribute("pageBean", pageBean);

        return "admin/admin_info/manage";
    }

    @GetMapping("/foreign_key")
    public String foreignKeyAdmins(@RequestParam(value = "page", defaultValue = "1") int page,
                                   @RequestParam(value = "search", required = false) String search,
                                   @RequestParam(value = "sort", required = false) String sort,
                                   @RequestParam(value = "pageChange", required = false) String pageChange,
                                   Model model,
                                   HttpSession session) {
        AdminInfoFilterDTO_admin filter = (AdminInfoFilterDTO_admin) session.getAttribute("adminInfoFilter");
        if (filter == null || (search == null && sort == null && pageChange == null)) {
            filter = new AdminInfoFilterDTO_admin();
            session.setAttribute("adminInfoFilter", filter);
        }

        int pageSize = 10;
        int paginationCnt = 10;
        int offset = (page - 1) * pageSize;
        filter.setOffset(offset);
        filter.setPageSize(pageSize);

        List<AdminInfoDTO_admin> adminList = adminInfoService.getAdminsByFilterAndSort(filter);
        int totalAdmins = adminInfoService.getAdminCountByFilter(filter);
        PageBean_admin pageBean = new PageBean_admin(totalAdmins, page, pageSize, paginationCnt);

        model.addAttribute("adminList", adminList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBean.getPageCnt());
        model.addAttribute("filter", filter);
        model.addAttribute("pageBean", pageBean);

        return "admin/admin_info/foreign_key";
    }

    @PostMapping("/search")
    public String searchAdmins(
            @RequestParam(required = false) Integer admin_idx,
            @RequestParam(required = false) String admin_name,
            @RequestParam(required = false) String admin_nickname,
            @RequestParam(required = false) String login_id,
            @RequestParam(required = false) String admin_type,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam String returnJSP,
            @ModelAttribute AdminInfoFilterDTO_admin filter,
            HttpSession session) {

        filter.setAdmin_idx(admin_idx);
        filter.setAdmin_name(admin_name);
        filter.setAdmin_nickname(admin_nickname);
        filter.setLogin_id(login_id);
        filter.setAdmin_type(admin_type);
        filter.setPhone(phone);
        filter.setEmail(email);

        session.setAttribute("adminInfoFilter", filter);

        if(returnJSP.equals("manageJSP")) {
            return "redirect:/admin/adminInfo/manage?page=1&search=true";
        } else if (returnJSP.equals("foreignJSP")) {
            return "redirect:/admin/adminInfo/foreign_key?page=1&search=true";
        } else {
            System.out.println("컨트롤러의 searchAdmins 메서드에서 오류 발생");
            return "admin/admin_info/manage";
        }
    }

    @GetMapping("/sort")
    public String sortAdmins(@RequestParam("sortField") String sortField,
                             @RequestParam String returnJSP, HttpSession session) {
        AdminInfoFilterDTO_admin filter = (AdminInfoFilterDTO_admin) session.getAttribute("adminInfoFilter");

        if (filter != null) {
            switch (sortField) {
                case "adminIdx":
                    filter.setAdminIdxOrder(toggleOrder(filter.getAdminIdxOrder()));
                    break;
                case "adminName":
                    filter.setAdminNameOrder(toggleOrder(filter.getAdminNameOrder()));
                    break;
                case "nickName":
                    filter.setNickNameOrder(toggleOrder(filter.getNickNameOrder()));
                    break;
                case "loginId":
                    filter.setLoginIdOrder(toggleOrder(filter.getLoginIdOrder()));
                    break;
                case "type":
                    filter.setAdminTypeOrder(toggleOrder(filter.getAdminTypeOrder()));
                    break;
                case "phone":
                    filter.setPhoneOrder(toggleOrder(filter.getPhoneOrder()));
                    break;
                case "email":
                    filter.setEmailOrder(toggleOrder(filter.getEmailOrder()));
                    break;
                default:
                    break;
            }
            session.setAttribute("adminInfoFilter", filter);
        }

        if(returnJSP.equals("manageJSP")) {
            return "redirect:/admin/adminInfo/manage?page=1&sort=true";
        } else if (returnJSP.equals("foreignJSP")) {
            return "redirect:/admin/adminInfo/foreign_key?page=1&sort=true";
        } else {
            System.out.println("컨트롤러의 sortAdmins 메서드에서 오류 발생");
            return "admin/admin_info/manage";
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
    public String addAdminInfoForm() {
        return "admin/admin_info/add";
    }

    @PostMapping("/add")
    public String addAdminInfo(@ModelAttribute AdminInfoDTO_admin admin, @RequestParam("passwordConfirm") String passwordConfirm, Model model) {
        // 비밀번호 일치 확인
        if (!admin.getLogin_pwd().equals(passwordConfirm)) {
            model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
            return "admin/admin_info/add";
        }

        // 닉네임 중복 체크
        if (adminInfoService.checkNicknameExists(admin.getAdmin_nickname(), null)) {
            model.addAttribute("errorMessage", "닉네임이 이미 존재합니다.");
            return "admin/admin_info/add";
        }

        // 로그인 ID 중복 체크
        if (adminInfoService.checkLoginIdExists(admin.getLogin_id(), null)) {
            model.addAttribute("errorMessage", "로그인 ID가 이미 존재합니다.");
            return "admin/admin_info/add";
        }

        // 관리자 정보 추가
        adminInfoService.insertAdmin(admin);
        return "redirect:/admin/adminInfo/manage";
    }

    @GetMapping("/modify/{id}")
    public ModelAndView modifyAdminInfoForm(@PathVariable("id") int id) {
        AdminInfoDTO_admin admin = adminInfoService.getAdminById(id);
        if (admin == null) {
            return new ModelAndView("redirect:/admin/adminInfo/manage");
        }
        ModelAndView mav = new ModelAndView("admin/admin_info/modify");
        mav.addObject("admin", admin);
        return mav;
    }

    @PostMapping("/modify")
    public String modifyAdminInfo(@ModelAttribute AdminInfoDTO_admin admin,
                                  @RequestParam("currentPassword") String currentPassword,
                                  @RequestParam(value = "newPassword", required = false) String newPassword,
                                  @RequestParam(value = "newPasswordConfirm", required = false) String newPasswordConfirm,
                                  @RequestParam(value = "pwChange", defaultValue = "false") Boolean pwChange,
                                  @RequestParam(value = "delete_pic", defaultValue = "false") Boolean deletePic,
                                  Model model) {
        AdminInfoDTO_admin existingAdmin = adminInfoService.getAdminById(admin.getAdmin_idx());
        
        // 현재 비밀번호 확인
        if (!existingAdmin.getLogin_pwd().equals(currentPassword)) {
            model.addAttribute("errorMessage", "현재 비밀번호가 일치하지 않습니다.");
            return "admin/admin_info/modify";
        }

        // 비밀번호 변경 여부 확인
        if (pwChange) {
            if (newPassword == null || !newPassword.equals(newPasswordConfirm)) {
                model.addAttribute("errorMessage", "새 비밀번호가 일치하지 않습니다.");
                return "admin/admin_info/modify";
            }
            admin.setLogin_pwd(newPassword);
        } else {
            admin.setLogin_pwd(existingAdmin.getLogin_pwd());
        }

        // 닉네임 중복 체크
        if (adminInfoService.checkNicknameExists(admin.getAdmin_nickname(), admin.getAdmin_idx())) {
            model.addAttribute("errorMessage", "닉네임이 이미 존재합니다.");
            return "admin/admin_info/modify";
        }

        // 로그인 ID 중복 체크
        if (adminInfoService.checkLoginIdExists(admin.getLogin_id(), admin.getAdmin_idx())) {
            model.addAttribute("errorMessage", "로그인 ID가 이미 존재합니다.");
            return "admin/admin_info/modify";
        }

        // 관리자 정보 업데이트
        adminInfoService.updateAdmin(admin, deletePic);
        
        // 수정 완료 후 상세 페이지로 리다이렉트
        return "redirect:/admin/adminInfo/detail/" + admin.getAdmin_idx();
    }
    
    @GetMapping("/detail/{id}")
    public ModelAndView detailAdminInfo(@PathVariable("id") int id) {
        AdminInfoDTO_admin admin = adminInfoService.getAdminById(id);
        ModelAndView mav = new ModelAndView("admin/admin_info/detail");
        mav.addObject("admin", admin);
        return mav;
    }

    @GetMapping("/delete_check/{id}")
    public ModelAndView deleteAdminInfoForm(@PathVariable("id") int id, Model model) {
        AdminInfoDTO_admin admin = adminInfoService.getAdminById(id);
        if (admin == null) {
            return new ModelAndView("redirect:/admin/adminInfo/manage");
        }
        ModelAndView mav = new ModelAndView("admin/admin_info/delete_check");
        mav.addObject("admin_idx", id);
        return mav;
    }

    @PostMapping("/delete_check/{id}")
    public ModelAndView deleteAdmin(@PathVariable("id") int id, @RequestParam("password") String password) {
        AdminInfoDTO_admin admin = adminInfoService.getAdminById(id);
        ModelAndView mav = new ModelAndView("admin/admin_info/delete_check");
        
        if (admin == null || !admin.getLogin_pwd().equals(password)) {
            mav.addObject("errorMessage", "비밀번호가 일치하지 않습니다.");
            mav.addObject("admin_idx", id);
            return mav;
        }
        
        adminInfoService.deleteAdmin(id);

        mav.setViewName("redirect:/admin/adminInfo/manage?deleteSuccess=true");
        return mav;
    }

    
    
    
    // 닉네임 중복 체크
    @GetMapping("/checkNickname")
    @ResponseBody
    public boolean checkNickname(@RequestParam("admin_nickname") String admin_nickname,
                                 @RequestParam(value = "admin_idx", required = false) Integer admin_idx) {
        return adminInfoService.checkNicknameExists(admin_nickname, admin_idx);
    }

    // 로그인 아이디 중복 체크
    @GetMapping("/checkLoginId")
    @ResponseBody
    public boolean checkLoginId(@RequestParam("login_id") String login_id,
                                @RequestParam(value = "admin_idx", required = false) Integer admin_idx) {
        return adminInfoService.checkLoginIdExists(login_id, admin_idx);
    }
}
