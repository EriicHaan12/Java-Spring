package com.springproj.controller.member;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springproj.domain.MemberVo;
import com.springproj.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Inject
	private MemberService service; //
	
	@RequestMapping(value="myPage")
	public void myPage(@RequestParam("userId")String userId, Model model) throws Exception {
		System.out.println(userId+"의 개인정보 페이지");
	
		//session에 Login한 덕분에 LoginMember 객체가 남아있지만, 유저가 로그인한 상태에서 유저 정보를 수정할 수있으므로,
		//다시 service를 호출해주는 것이 좋다.
		
		MemberVo memberInfo =  service.getUserInfo(userId);
	if(memberInfo != null) {
		model.addAttribute("memberInfo",memberInfo);
	}
	}
	
	
}
