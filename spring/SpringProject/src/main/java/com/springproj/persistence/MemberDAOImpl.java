package com.springproj.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.springproj.domain.LoginDTO;
import com.springproj.domain.MemberVo;
import com.springproj.domain.SessionDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	@Inject
	private SqlSession ses;// SqlSessionTemplate 객체 주입

	private static String ns = "com.springproj.mappers.memberMapper";

	@Override
	public MemberVo selectMemberWithLoginDTO(LoginDTO login) throws Exception {

		return ses.selectOne(ns + ".login", login);
	}

	@Override
	public int updateRemember(SessionDTO ses) throws Exception {

		return this.ses.update(ns + ".updateRemember", ses);
	}

	@Override
	public MemberVo selectRemember(String sesIdCookie) throws Exception {

		return this.ses.selectOne(ns + ".selectRemember", sesIdCookie);
	}

}
