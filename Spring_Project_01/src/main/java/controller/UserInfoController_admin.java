package controller;

import dto.PageBean_admin;
import dto.UserInfoDTO_admin;
import filter.UserInfoFilterDTO_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.UserInfoService_admin;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/userInfo")
public class UserInfoController_admin {

    @Autowired
    private UserInfoService_admin userInfoService;

    @GetMapping("/manage")
    public String manageUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "search", required = false) String search,
                              @RequestParam(value = "sort", required = false) String sort,
                              @RequestParam(value = "pageChange", required = false) String pageChange,
                              Model model,
                              HttpSession session) {
        UserInfoFilterDTO_admin filter = (UserInfoFilterDTO_admin) session.getAttribute("userInfoFilter");
        if (filter == null || (search == null && sort == null && pageChange == null)) {
            filter = new UserInfoFilterDTO_admin();
            session.setAttribute("userInfoFilter", filter);
        }

        int pageSize = 10;
        int paginationCnt = 10;
        int offset = (page - 1) * pageSize;
        filter.setOffset(offset);
        filter.setPageSize(pageSize);

        List<UserInfoDTO_admin> userList = userInfoService.getUsersByFilterAndSort(filter);
        int totalUsers = userInfoService.getUserCountByFilter(filter);
        PageBean_admin pageBean = new PageBean_admin(totalUsers, page, pageSize, paginationCnt);

        // 유저 이미지 넣기
        for(UserInfoDTO_admin user : userList) {
        	int temp_idx = user.getUser_idx();
            String userPic = userInfoService.getUserPicByUserIdx(temp_idx);
            user.setUser_pic(userPic);
        }
        
        model.addAttribute("userList", userList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBean.getPageCnt());
        model.addAttribute("filter", filter);
        model.addAttribute("pageBean", pageBean);

        return "admin/user_info/manage";
    }

    @GetMapping("/foreign_key")
    public String foreignKeyUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                                  @RequestParam(value = "search", required = false) String search,
                                  @RequestParam(value = "sort", required = false) String sort,
                                  @RequestParam(value = "pageChange", required = false) String pageChange,
                                  Model model,
                                  HttpSession session) {
        UserInfoFilterDTO_admin filter = (UserInfoFilterDTO_admin) session.getAttribute("userInfoFilter");
        if (filter == null || (search == null && sort == null && pageChange == null)) {
            filter = new UserInfoFilterDTO_admin();
            session.setAttribute("userInfoFilter", filter);
        }

        int pageSize = 10;
        int paginationCnt = 10;
        int offset = (page - 1) * pageSize;
        filter.setOffset(offset);
        filter.setPageSize(pageSize);

        List<UserInfoDTO_admin> userList = userInfoService.getUsersByFilterAndSort(filter);
        int totalUsers = userInfoService.getUserCountByFilter(filter);
        PageBean_admin pageBean = new PageBean_admin(totalUsers, page, pageSize, paginationCnt);

        model.addAttribute("userList", userList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBean.getPageCnt());
        model.addAttribute("filter", filter);
        model.addAttribute("pageBean", pageBean);

        return "admin/user_info/foreign_key";
    }

    @PostMapping("/search")
    public String searchUsers(
            @RequestParam(required = false) Integer user_idx,
            @RequestParam(required = false) String user_name,
            @RequestParam(required = false) String user_nickname,
            @RequestParam(required = false) String user_id,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String detailaddress,
            @RequestParam(required = false) String addressnum,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam String returnJSP,
            @ModelAttribute UserInfoFilterDTO_admin filter,
            HttpSession session) {

        filter.setUser_idx(user_idx);
        filter.setUser_name(user_name);
        filter.setUser_nickname(user_nickname);
        filter.setUser_id(user_id);
        filter.setAddress(address);
        filter.setDetailaddress(detailaddress);
        filter.setAddressnum(addressnum);
        filter.setPhone(phone);
        filter.setEmail(email);

        session.setAttribute("userInfoFilter", filter);

        if (returnJSP.equals("manageJSP")) {
            return "redirect:/admin/userInfo/manage?page=1&search=true";
        } else if (returnJSP.equals("foreignJSP")) {
            return "redirect:/admin/userInfo/foreign_key?page=1&search=true";
        } else {
            return "admin/user_info/manage";
        }
    }

    @GetMapping("/sort")
    public String sortUsers(@RequestParam("sortField") String sortField,
                            @RequestParam String returnJSP, HttpSession session) {
        UserInfoFilterDTO_admin filter = (UserInfoFilterDTO_admin) session.getAttribute("userInfoFilter");

        if (filter != null) {
            switch (sortField) {
                case "userIdx":
                    filter.setUserIdxOrder(toggleOrder(filter.getUserIdxOrder()));
                    break;
                case "userName":
                    filter.setUserNameOrder(toggleOrder(filter.getUserNameOrder()));
                    break;
                case "userNickname":
                    filter.setUserNicknameOrder(toggleOrder(filter.getUserNicknameOrder()));
                    break;
                case "userId":
                    filter.setUserIdOrder(toggleOrder(filter.getUserIdOrder()));
                    break;
                case "address":
                    filter.setAddressOrder(toggleOrder(filter.getAddressOrder()));
                    break;
                case "detailAddress":
                    filter.setDetailAddressOrder(toggleOrder(filter.getDetailAddressOrder()));
                    break;
                case "addressNum":
                    filter.setAddressNumOrder(toggleOrder(filter.getAddressNumOrder()));
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
            session.setAttribute("userInfoFilter", filter);
        }

        if (returnJSP.equals("manageJSP")) {
            return "redirect:/admin/userInfo/manage?page=1&sort=true";
        } else if (returnJSP.equals("foreignJSP")) {
            return "redirect:/admin/userInfo/foreign_key?page=1&sort=true";
        } else {
            return "admin/user_info/manage";
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

    @GetMapping("/modify/{id}")
    public ModelAndView modifyUserInfoForm(@PathVariable("id") int id) {
        UserInfoDTO_admin user = userInfoService.getUserById(id);
        if (user == null) {
            return new ModelAndView("redirect:/admin/userInfo/manage");
        }
        ModelAndView mav = new ModelAndView("admin/user_info/modify");
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("/modify")
    public String modifyUserInfo(@ModelAttribute UserInfoDTO_admin user,
    							Model model) {
    	if (userInfoService.checkNicknameExists(user.getUser_nickname(), user.getUser_idx())) {
            model.addAttribute("errorMessage", "닉네임이 이미 존재합니다.");
            return "admin/user_info/modify";
    	}
        userInfoService.updateUser(user);
        //return "redirect:/admin/userInfo/manage";
        
        // 수정 완료 후 상세 페이지로 리다이렉트
        return "redirect:/admin/userInfo/detail/" + user.getUser_idx();
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detailUserInfo(@PathVariable("id") int id) {
        UserInfoDTO_admin user = userInfoService.getUserById(id);
        String userPic = userInfoService.getUserPicByUserIdx(id);
        user.setUser_pic(userPic);
        
        ModelAndView mav = new ModelAndView("admin/user_info/detail");
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userInfoService.deleteUser(id);
        return "redirect:/admin/userInfo/manage";
    }
    
    // 닉네임 중복 체크
    @GetMapping("/checkNickname")
    @ResponseBody
    public boolean checkNickname(@RequestParam("user_nickname") String user_nickname,
            @RequestParam(value = "user_idx", required = false) Integer user_idx) {
    	return userInfoService.checkNicknameExists(user_nickname, user_idx);
    }
    
}
