package kr.co.soldesk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.soldesk.beans.UserBean;

public class CheckLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");

        System.out.println("Interceptor: " + loginUserBean);
        System.out.println("User is logged in: " + (loginUserBean != null && loginUserBean.isUserLogin()));

        // 로그인하지 않았으면
        if (loginUserBean == null || !loginUserBean.isUserLogin()) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/user/not_login");
            return false;
        }
        // 로그인 상태
        return true;
    }
}
