package controller;

import dto.AdminInfoDTO_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.AdminInfoService_admin;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController_admin {

    @Autowired
    private AdminInfoService_admin adminInfoService;

    @GetMapping("/login")
    public String login(HttpSession session, Model model) {
        String errorMessage = (String) session.getAttribute("errorMessage");
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            session.removeAttribute("errorMessage");
        }
        return "admin/login";
    }

    @PostMapping("/login")
    public String loginProcess(@RequestParam("login_id") String login_id,
                               @RequestParam("login_pwd") String login_pwd,
                               HttpSession session) {
        AdminInfoDTO_admin admin = adminInfoService.getAdminByLoginId(login_id);
        if (admin != null && admin.getLogin_pwd().equals(login_pwd)) {
            session.setAttribute("admin", admin);
            return "redirect:/admin/home";
        } else {
            session.setAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "redirect:/admin/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        AdminInfoDTO_admin admin = (AdminInfoDTO_admin) session.getAttribute("admin");
        model.addAttribute("admin", admin);
        return "admin/home";
    }

    @GetMapping("/password_check")
    public String passwordCheck(@RequestParam("returnFolder") String returnFolder, Model model) {
        model.addAttribute("returnFolder", returnFolder);
        return "admin/password_check";
    }

    @PostMapping("/checkPassword")
    public String checkPassword(@RequestParam("admin_idx") int admin_idx,
                                @RequestParam("login_pwd") String login_pwd,
                                @RequestParam("action") String action,
                                @RequestParam("returnFolder") String returnFolder,
                                HttpSession session, Model model) {
        AdminInfoDTO_admin admin = adminInfoService.getAdminById(admin_idx);
        if (admin != null && admin.getLogin_pwd().equals(login_pwd)) {
            if ("modify".equals(action)) {
                return "redirect:/admin/" + returnFolder + "/modify/" + admin_idx;
            } else if ("delete".equals(action)) {
                return "redirect:/admin/" + returnFolder + "/deleteConfirm/" + admin_idx;
            }
        }
        model.addAttribute("errorMessage", "비밀번호가 올바르지 않습니다.");
        model.addAttribute("returnFolder", returnFolder);
        return "admin/password_check";
    }
}
