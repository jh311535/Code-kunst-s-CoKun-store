package kr.co.soldesk.controller;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.soldesk.beans.UserBean;



@Controller
public class HomeController {
	
	//�̸��� ���ؼ� ���
	@Resource(name="loginUserBean")
	private UserBean loginUserBean;
	
	/*
	//�α��ν� ������������
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		System.out.println(loginUserBean);
		return "redirect:/main"; //
	}
	*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		System.out.println(loginUserBean);
		System.out.println(request.getServletContext().getRealPath("/"));
		return "redirect:/main"; //
	}
}
