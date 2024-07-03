package interceptor;

import dto.AdminInfoDTO_admin;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 세션에서 관리자 정보 확인
        AdminInfoDTO_admin admin = (AdminInfoDTO_admin) request.getSession().getAttribute("admin");

        // 관리자가 로그인 상태인지 확인
        if (admin == null) {
            // 로그인 상태가 아니면 경고창을 표시하고 로그인 페이지로 리다이렉트
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write("<script>alert('잘못된 접근입니다. 관리자 로그인이 필요합니다.'); location.href='" + request.getContextPath() + "/admin/login';</script>");
            return false;
        }
        /*
        // 관리자가 로그인 상태인지 확인 (수정 전 ver)
        if (admin == null) {
            // 로그인 상태가 아니면 로그인 페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return false;
        }
        */

        // 관리자 등급 확인
        if ("withdraw".equals(admin.getAdmin_type())) {
            // 관리자 등급이 withdraw이면 로그아웃 후 로그인 페이지로 리다이렉트
            request.getSession().invalidate();
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write("<script>alert('접근 권한이 없습니다.'); location.href='" + request.getContextPath() + "/admin/login';</script>");
            return false;
        }

        return true; // 관리자가 로그인 상태이면 요청을 계속 진행
    }
}

