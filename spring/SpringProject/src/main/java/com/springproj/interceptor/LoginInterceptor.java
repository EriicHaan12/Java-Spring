package com.springproj.interceptor;

import java.sql.Timestamp;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.springproj.domain.MemberVo;
import com.springproj.domain.SessionDTO;
import com.springproj.etc.DestinationPathProc;
import com.springproj.service.MemberService;

//제어를 빼앗아 실제 로그인을 처리하는 interceptor 이다.
public class LoginInterceptor extends HandlerInterceptorAdapter {

	// Interceptor는 Spring Container에 접근하여 Spring 객체에 접근이 가능
	@Inject
	private MemberService service;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("/login GET: 로그인 처리 하러 왔음...loginInterceptor");
		HttpSession ses = request.getSession();

		// DestinationPathProc.returnPathProc(request);

		// ses.removeAttribute("loginMember");// 세션에 남아있는 데이터 지워주기
		// ses.removeAttribute("returnPath");

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("/Login:POST 로그인 처리... LoginInterceptor의  postHandle");

		HttpSession ses = request.getSession();
		// 여기서 가져온 ModelMap은 이전 Home컨트롤러 단에서 바인딩한 Model 객체를 가지고 있는 객체이다
		ModelMap mm = modelAndView.getModelMap();

		MemberVo loginMember = (MemberVo) mm.get("loginMember");
		if (loginMember != null) {
			// 1) 사용자가 로그인 시에 자동로그인을 체크하여 로그인을 했을 때,
			// 쿠키에 현재 세션 ID값을 저장하고, DB에도 세션 ID값과 만료일을 저장(update)해야함.

			System.out.println("로그인 성공");
			ses.setAttribute("loginMember", loginMember); // 세션에 로그인 한 유저의 정보를 바인딩

			if (request.getParameter("remember") != null) {// 자동로그인 체크박스에 체크가 되어있을 때
				System.out.println("자동로그인을 위해 쿠키 남기기");

				String sesId = ses.getId();
				Timestamp now = new Timestamp(System.currentTimeMillis()); // 현재 시간 가져오기
				Timestamp sesLimit = new Timestamp(System.currentTimeMillis() + (60 * 60 * 24 * 7 * 1000));

				System.out.println("현재시간 : " + now.toString());
				System.out.println("만료일 : " + sesLimit.toString());

				// DB에 session 정보 저장
				service.remember(new SessionDTO(sesId, sesLimit, loginMember.getUserId()));

				Cookie rememberCookie = new Cookie("ses", sesId);
				rememberCookie.setPath("/");// 쿠키 저장할 path
				rememberCookie.setMaxAge(60 * 60 * 24 * 7); // 쿠키 만료일(30일 동안)
				response.addCookie(rememberCookie);// 쿠키 저장

			}

			String returnPath = "";
			if (ses.getAttribute("returnPath") != null) {
				returnPath = (String) ses.getAttribute("returnPath");
			}
			response.sendRedirect((!returnPath.equals("")) ? returnPath : "/");
			// 삼항연산자를 통해 returnPath가 ""이 면 index.jsp 이고 아닐 경우 넘겨받은 returnPath 로 이동 시키기
		}
	}
}
