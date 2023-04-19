package com.springproj.service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproj.domain.LoginDTO;
import com.springproj.domain.MemberPointVo;
import com.springproj.domain.MemberVo;
import com.springproj.domain.SessionDTO;
import com.springproj.persistence.BoardDAO;
import com.springproj.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao; // MemberDAO 호출을 위한 객체 주입

	@Inject
	private BoardDAO bdao;// BoardDAO 객체 주입

	@Override
	@Transactional
	public MemberVo login(LoginDTO login, HttpServletRequest req) throws Exception {

		// 1. 로그인 처리
		MemberVo loginMember = dao.selectMemberWithLoginDTO(login);

		// 2. 로그인 성공 되면 로그인한 유저에게 포인트 지급
		if (loginMember != null) {
			bdao.addPointToMember(new MemberPointVo(0, loginMember.getUserId(), null, "로그인", 0));

		}
		return loginMember;
	}

	@Override
	public boolean remember(SessionDTO ses) throws Exception {
		System.out.println("serviceImpl : " + ses.toString());

		int result = dao.updateRemember(ses);
		if (result == 1) {
			return true;
		}

		return false;
	}

	@Override
	public MemberVo sesValid(String sesIdCookie) throws Exception {
		
		MemberVo validMember = dao.selectRemember(sesIdCookie);
		
		return validMember;
	}

	@Override
	public MemberVo getUserInfo(String userId) throws Exception {
		

		return 	dao.selectMemberWithUserId(userId);
	}

}
