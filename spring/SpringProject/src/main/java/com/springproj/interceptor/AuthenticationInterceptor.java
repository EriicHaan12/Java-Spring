package com.springproj.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.springproj.domain.MemberVo;
import com.springproj.etc.DestinationPathProc;

//제어를 빼앗아 로그인을 했는지 안했는지 검사하는 interceptor
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		boolean result = false;
		System.out.println("로그인을 했는지 안했는지 검사 ");

		HttpSession ses = request.getSession();
		if (ses.getAttribute("loginMember") == null) {// 로그인 안한 상태

			// 로그인 이전에 있었던 경로를 남기자
			DestinationPathProc.returnPathProc(request);

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
			result = true;
		}
		return result;
	}

}
