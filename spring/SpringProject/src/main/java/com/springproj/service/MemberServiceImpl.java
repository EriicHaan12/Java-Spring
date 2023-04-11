package com.springproj.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springproj.domain.LoginDTO;
import com.springproj.domain.MemberPointVo;
import com.springproj.domain.MemberVo;
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
	public MemberVo login(LoginDTO login) throws Exception {

		// 1. 로그인 처리
		MemberVo loginMember = dao.selectMemberWithLoginDTO(login);

		// 2. 로그인 성공 되면 로그인한 유저에게 포인트 지급
		if (loginMember != null) {
			bdao.addPointToMember(new MemberPointVo(0, loginMember.getUserId(), null, "로그인", 0));
		}
		return loginMember;
	}

}