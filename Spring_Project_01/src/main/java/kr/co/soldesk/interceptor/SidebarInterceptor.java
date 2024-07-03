package kr.co.soldesk.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.soldesk.beans.SidebarDTO;
import kr.co.soldesk.service.SidebarService;

public class SidebarInterceptor implements HandlerInterceptor{
	
	private SidebarService sidebarService;

	public SidebarInterceptor(SidebarService sidebarService) {
		this.sidebarService = sidebarService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		List<SidebarDTO> sidebarList=sidebarService.getSidebarList();
		request.setAttribute("sidebarList", sidebarList);
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	

}
