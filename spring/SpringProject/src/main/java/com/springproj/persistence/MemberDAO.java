package com.springproj.persistence;

import com.springproj.domain.LoginDTO;
import com.springproj.domain.MemberVo;
import com.springproj.domain.SessionDTO;

public interface MemberDAO {
	// 로그인 처리
	MemberVo selectMemberWithLoginDTO(LoginDTO login) throws Exception;

	// 자동로그인
	int updateRemember(SessionDTO ses) throws Exception;

	// 쿠키에 저장된 세션이 유효한지 검사
	MemberVo selectRemember(String sesIdCookie) throws Exception;

	//유저 아이디로 유저 정보 가져오기(개인정보 페이지)
	MemberVo selectMemberWithUserId(String userId)throws Exception;

}
