package kr.co.soldesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.co.soldesk.service.UserService;


//��������
@RestController
public class RestApiController {
	
	@Autowired
	private UserService userService;
	
	//���̵� �ߺ��˻�
	@GetMapping("/user/checkUserIdExist/{user_id}")
	public String checkUserIdExist(@PathVariable String user_id) {
		//@PathVariable: �ּҿ� ������ ���̱�
		boolean chk=userService.checkUserIdExist(user_id);
		return chk+"";
	}
	
	//�̸��� �ߺ��˻�
	@GetMapping("/user/checkUserEmailExist/{email}")
	public String checkUserEmailExist(@PathVariable String email) {
		//@PathVariable: �ּҿ� ������ ���̱�
		boolean chk=userService.checkUserEmailExist(email);
		return chk+"";
	}
	
	//�г��� �ߺ��˻�
	@GetMapping("/user/checkUserNicknameExist/{user_nickname}")
	public String checkUserNicknameExist(@PathVariable String user_nickname) {
		//@PathVariable: �ּҿ� ������ ���̱�
		boolean chk=userService.checkUserNicknameExist(user_nickname);
		return chk+"";
	}

}