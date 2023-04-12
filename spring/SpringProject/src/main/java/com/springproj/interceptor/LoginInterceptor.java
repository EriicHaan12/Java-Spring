package com.springproj.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.springproj.domain.MemberVo;
import com.springproj.etc.DestinationPathProc;

//제어를 빼앗아 실제 로그인을 처리하는 interceptor 이다.
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("로그인 처리 하러 왔음...loginInterceptor");
		HttpSession ses = request.getSession();
		
		//DestinationPathProc.returnPathProc(request);
		
		// ses.removeAttribute("loginMember");// 세션에 남아있는 데이터 지워주기
		// ses.removeAttribute("returnPath");
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("로그인 처리... LoginInterceptor의  postHandle");

		HttpSession ses = request.getSession();
		// 여기서 가져온 ModelMap은 이전 Home컨트롤러 단에서 바인딩한 Model 객체를 가지고 있는 객체이다
		ModelMap mm = modelAndView.getModelMap();
		MemberVo loginMember = (MemberVo) mm.get("loginMember");
		if (loginMember != null) {
			System.out.println("로그인 성공");
			ses.setAttribute("loginMember", loginMember); // 세션에 로그인 한 유저의 정보를 바인딩

			String returnPath = "";
			if (ses.getAttribute("returnPath") != null) {
				returnPath = (String) ses.getAttribute("returnPath");
			}

			response.sendRedirect((!returnPath.equals("")) ? returnPath : "/"); 
			// 삼항연산자를 통해 returnPath가 ""이 면 index.jsp 이고 아닐 경우 넘겨받은 returnPath 로 이동 시키기
		}
	}
}
