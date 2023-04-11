package com.springproj.controller.member;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springproj.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Inject
	private MemberService service; //
	
	@RequestMapping(value="myPage")
	public void myPage(@RequestParam("userId")String userId) {
		System.out.println(userId+"의 개인정보 페이지");
	
	
	}
	
	
}
