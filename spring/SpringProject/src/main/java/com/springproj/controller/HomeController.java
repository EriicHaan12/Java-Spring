package com.springproj.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.springproj.domain.LoginDTO;
import com.springproj.domain.MemberVo;
import com.springproj.service.MemberService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	private MemberService service; // MemberService 를 호출하기 위한 service 주입

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "index";
	}

	@RequestMapping("exam")
	public void examInterceptor() {
		System.out.println("ExampleInterceptor() 동작 테스트 ");
	}

	// Get방식으로 호출 (default 가 get방식이라 method= RequestMethod.GET을 생략해도 된다.)
	@RequestMapping("login")
	public void login() {
		// 먼저 LoginInterceptor 의 preHandle() 호출

		// login.jsp렌더링
	}
	// Post방식으로 호출

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public void login(LoginDTO login, Model model, RedirectAttributes rttr) throws Exception {

		// 먼저 LoginInterceptor 의 preHandle() 호출
		System.out.println(login.toString());

		MemberVo loginMember = service.login(login);

		System.out.println(loginMember.toString());
		//// 먼저 LoginInterceptor 의 postHandle() 호출
		if (loginMember != null) {
			model.addAttribute("loginMember", loginMember);
		}

		// loginInterceptor의 postHandle() 호출

	}

	@RequestMapping("logout")
	public String logout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession ses = req.getSession();
		System.out.println("로그아웃");

		//4) 유저가 로그인을 하여 활동하다가 로그아웃을 눌렀을 경우 쿠키가 있다면, 쿠키 삭제
			Cookie sesCookie = WebUtils.getCookie(req, "ses");
			
			if(sesCookie!= null) {
				sesCookie.setMaxAge(0); // 만료 시키기, 쿠키 삭제
				res.addCookie(sesCookie);	
			}
			
		ses.removeAttribute("loginMember");
		ses.removeAttribute("returnPath");
		ses.invalidate(); // 무효화 시키기
		
		String uri = req.getRequestURI();
		System.out.println("로그아웃 할 때 uri : " + uri);
		return "redirect:/"; // 로그아웃 하면 index.jsp 로 redirect 강제 이동 시키기.

	}

}
