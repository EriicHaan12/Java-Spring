package com.springproj.service;

import javax.servlet.http.HttpServletRequest;

import com.springproj.domain.LoginDTO;
import com.springproj.domain.MemberVo;
import com.springproj.domain.SessionDTO;

public interface MemberService {
	//로그인 처리
	MemberVo login(LoginDTO login, HttpServletRequest req) throws Exception;
	
	//자동로그인 처리
	boolean remember(SessionDTO ses)throws Exception;
	
	//쿠키에 저장된 세션이 유효한지 아닌지 검사
	MemberVo sesValid(String sesIdCookie)throws Exception;

	//사용자 정보 가져오기
	MemberVo getUserInfo(String userId)throws Exception;
}
