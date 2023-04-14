package com.springproj.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.springproj.domain.MemberVo;
import com.springproj.etc.DestinationPathProc;
import com.springproj.service.MemberService;

//제어를 빼앗아 로그인을 했는지 안했는지 검사하는 interceptor
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	@Inject
	private MemberService service;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("로그인을 했는지 안했는지 검사 ");
		DestinationPathProc.returnPathProc(request);

		HttpSession ses = request.getSession();
		if (ses.getAttribute("loginMember") == null) {// 로그인 안한 상태

			// 로그인 이전에 있었던 경로를 남기자
			// "ses" 쿠키가 있는지 없는지 검사, 만약 있을 경우 (일단 있는지 검사)
			// request.getCookies();

			// request.getCookies()를 이용하여 쿠키 배열을 얻고 쿠키 배열에서 이름이 "ses"인 쿠키를 반환해줌
			Cookie rememberCookie = WebUtils.getCookie(request, "ses");

			if (rememberCookie != null) {// 쿠키가 있다.
					
				// 2) 사용자가 로그인 하지 않은 상태지만, 쿠키가 저장되어 있는 경우
				// 쿠키에 저장되어 있는 세션 ID를 DB에 저장되어 있는 세션ID값과 비교하여 유효기간에 맞는 값인지 확인(만료일 체크)

				String sesIdCookie = rememberCookie.getValue(); // 세션에 저장되어 있는 쿠키값(sessionId)
				System.out.println(sesIdCookie + "의 쿠키가 있음");

				MemberVo validMember = service.sesValid(sesIdCookie);
				
				if (validMember != null) {// 유효한 쿠키
					ses.setAttribute("loginMember", validMember);// 로그인 처리
					//3)2번 조건에 해당 하는 유저라면 세션에 로그인 정보를 기록(로그인 성공)
					
					//이전 경로가 있다면 이전 경로로 이동
					String returnPath = "";
					if (ses.getAttribute("returnPath") != null) {
						returnPath = (String) ses.getAttribute("returnPath");
					}
					response.sendRedirect((!returnPath.equals("")) ? returnPath : "/");

					return false;
				}
			}
			response.sendRedirect("/login"); // 로그인 하러 보내버리기

		} else { // 로그인 한 상태
			String uri = request.getRequestURI();
			if (uri.indexOf("/modiBoard") != -1 || uri.indexOf("/remBoard") != -1) {
				System.out.println("수정페이지로 넘어가기 전 로그인 검사");

				MemberVo loginMember = (MemberVo) ses.getAttribute("loginMember");

				String writer = request.getParameter("writer");

				System.out.println("가져온 Writer : " + writer);

				if (!loginMember.getUserId().equals(writer)) {
					// 수정페이지 들어갈 때 작성자와 로그인한 유저가 같지 않을 때

					response.sendRedirect("viewBoard?no=" + request.getParameter("no") + "&status=notPermission");
					return false;
				}

			}
			return true;
		}
		return false;
	}

}
