package kr.co.soldesk.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.soldesk.beans.FindIdUserBean;
import kr.co.soldesk.beans.FindPwUserBean;
import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.service.UserService;
import kr.co.soldesk.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("tempUserLogin", new UserBean());
        return "user/login";
    }

    @PostMapping("/login_pro")
    public String login_pro(@ModelAttribute("tempUserLogin") UserBean tempUserLogin, BindingResult result, HttpSession session, Model model) {
        if (tempUserLogin.getUser_id() == null || tempUserLogin.getUser_id().isEmpty()) {
            result.rejectValue("user_id", "NotBlank");
        }
        if (tempUserLogin.getUser_pw() == null || tempUserLogin.getUser_pw().isEmpty()) {
            result.rejectValue("user_pw", "NotBlank");
        }

        if (result.hasErrors()) {
            model.addAttribute("tempUserLogin", tempUserLogin);
            return "user/login";
        }

        boolean loginResult = userService.checkUser(tempUserLogin);

        if (loginResult) {
            tempUserLogin.setUserLogin(true);
            session.setAttribute("loginUserBean", tempUserLogin);
            return "user/login_success";
        } else {
            model.addAttribute("tempUserLogin", tempUserLogin);
            return "user/login_fail";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "user/logout";
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("joinUserBean", new UserBean());
        return "user/join";
    }

    @PostMapping("/join_pro")
    public String join_pro(@Valid @ModelAttribute("joinUserBean") UserBean joinUserBean, BindingResult result) {
        if (result.hasErrors()) {
            return "user/join";
        }
        userService.addUserInfo(joinUserBean);
        return "user/join_success";
    }

    @GetMapping("/find_id")
    public String findIdForm(Model model) {
        model.addAttribute("FindIdBean", new FindIdUserBean());
        return "user/find_id";
    }

    @PostMapping("/find_id_pro")
    public String findIdSubmit(@Valid @ModelAttribute("FindIdBean") FindIdUserBean findIdBean, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/find_id";
        }

        String user_id = userService.findUserId(findIdBean.getUser_name(), findIdBean.getEmail(), findIdBean.getPhone());
        if (user_id != null) {
            model.addAttribute("user_id", user_id);
            return "user/findid_success";
        } else {
            result.reject("userNotFound", "해당 정보를 가진 사용자를 찾을 수 없습니다.");
            return "user/findid_fail";
        }
    }

    @GetMapping("/find_pw")
    public String findPwForm(Model model) {
        model.addAttribute("FindPwBean", new FindPwUserBean());
        return "user/find_pw";
    }

    @PostMapping("/find_pw_pro")
    public String findPwSubmit(@Valid @ModelAttribute("FindPwBean") FindPwUserBean findPwBean, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/find_pw";
        }

        String user_pw = userService.findUserPassword(findPwBean.getUser_id(), findPwBean.getUser_name(), findPwBean.getEmail());
        if (user_pw != null) {
            model.addAttribute("user_pw", user_pw);
            return "user/findpw_success";
        } else {
            result.reject("userNotFound", "해당 정보를 가진 사용자를 찾을 수 없습니다.");
            return "user/findpw_fail";
        }
    }

    @GetMapping("/check_password")
    public String checkPassword(Model model, @RequestParam("action") String action) {
        model.addAttribute("checkPasswordBean", new UserBean());
        model.addAttribute("action", action);
        return "user/check_password";
    }

    @PostMapping("/check_password_pro")
    public String checkPasswordPro(@ModelAttribute("checkPasswordBean") UserBean checkPasswordBean, BindingResult result, HttpSession session, @RequestParam("action") String action) {
        UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");
        if (loginUserBean == null || !loginUserBean.isUserLogin()) {
            return "redirect:/user/not_login";
        }

        boolean passwordCorrect = userService.checkPassword(loginUserBean.getUser_id(), checkPasswordBean.getUser_pw());
        if (passwordCorrect) {
            session.setAttribute("passwordVerified", true);
            if ("modify".equals(action)) {
                return "redirect:/user/modify";
            } else if ("delete".equals(action)) {
                return "redirect:/user/delete_account_confirm";
            }
        } else {
            result.rejectValue("user_pw", "IncorrectPassword");
            return "user/check_password_fail";
        }
        return "user/check_password_fail";
    }

    @GetMapping("/modify")
    public String modify(Model model, HttpSession session) {
        UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");
        if (loginUserBean == null || !loginUserBean.isUserLogin()) {
            return "redirect:/user/not_login";
        }

        Boolean passwordVerified = (Boolean) session.getAttribute("passwordVerified");
        if (passwordVerified == null || !passwordVerified) {
            return "redirect:/user/check_password?action=modify";
        }

        UserBean dbUserBean = userService.getUserInfo(loginUserBean);
        model.addAttribute("modifyUserBean", dbUserBean);

        return "user/modify";
    }

    @PostMapping("/modify_pro")
    public String modify_pro(@Valid @ModelAttribute("modifyUserBean") UserBean modifyUserBean, BindingResult result, HttpSession session) {
        UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");
        if (loginUserBean == null || !loginUserBean.isUserLogin()) {
            return "redirect:/user/not_login";
        }

        if (result.hasErrors()) {
            return "user/modify";
        }

        modifyUserBean.setUser_id(loginUserBean.getUser_id());
        modifyUserBean.setEmail(loginUserBean.getEmail());
        modifyUserBean.setUser_nickname(loginUserBean.getUser_nickname());

        userService.modifyUserInfo(modifyUserBean, loginUserBean.getUser_idx());

        loginUserBean.setUser_pw(modifyUserBean.getUser_pw());
        loginUserBean.setAddressnum(modifyUserBean.getAddressnum());
        loginUserBean.setAddress(modifyUserBean.getAddress());
        loginUserBean.setDetailaddress(modifyUserBean.getDetailaddress());
        loginUserBean.setPhone(modifyUserBean.getPhone());
        loginUserBean.setUser_name(modifyUserBean.getUser_name());

        session.setAttribute("loginUserBean", loginUserBean);

        return "redirect:/user/modify_success";
    }

    @GetMapping("/modify_success")
    public String modifySuccess() {
        return "user/modify_success";
    }

    @GetMapping("/not_login")
    public String not_login(HttpSession session, Model model) {
        UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");
        if (loginUserBean != null) {
            model.addAttribute("user_idx", loginUserBean.getUser_idx());
        } else {
            model.addAttribute("user_idx", "로그인되지 않음");
        }
        return "user/not_login";
    }
    
    @GetMapping("/mypage")
    public String mypage(Model model, HttpSession session) {
        UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");
        if (loginUserBean == null || !loginUserBean.isUserLogin()) {
            return "redirect:/user/not_login";
        }

        model.addAttribute("modifyUserBean", loginUserBean);
        return "user/mypage";
    }
    
    @GetMapping("/delete_account")
    public String deleteAccount(HttpSession session) {
        UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");
        if (loginUserBean == null || !loginUserBean.isUserLogin()) {
            return "redirect:/user/not_login";
        }

        userService.deleteUser(loginUserBean.getUser_idx());
        session.invalidate();

        return "user/delete_success";
    }

    @GetMapping("/delete_account_confirm")
    public String deleteAccountConfirm(HttpSession session, Model model) {
        UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");
        Boolean passwordVerified = (Boolean) session.getAttribute("passwordVerified");

        if (loginUserBean == null || !loginUserBean.isUserLogin() || passwordVerified == null || !passwordVerified) {
            return "redirect:/user/not_login";
        }

        model.addAttribute("deleteUserBean", loginUserBean);
        return "user/delete_account_confirm";
    }

    @PostMapping("/delete_account_pro")
    public String deleteAccountPro(HttpSession session) {
        UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");
        if (loginUserBean == null || !loginUserBean.isUserLogin()) {
            return "redirect:/user/not_login";
        }

        userService.deleteUser(loginUserBean.getUser_idx());
        session.invalidate();

        return "user/delete_success";
    }
    
 // 유효성 검사를 위해 필요함 :  Validator를 등록하는 역할
    @InitBinder // 최초로 읽고 감
    public void initBinder(WebDataBinder binder) {
       UserValidator validator1 = new UserValidator();
       binder.addValidators(validator1);
    } // method
}


