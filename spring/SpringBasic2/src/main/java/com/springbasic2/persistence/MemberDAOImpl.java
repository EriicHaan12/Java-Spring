package com.springbasic2.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.springbasic2.domain.MemberPointVO;
import com.springbasic2.domain.MemberVO;

@Repository // 현재 클래스가 DAO단임을 Spring컨테이너에게 알린다.
public class MemberDAOImpl implements MemberDAO {
	private static String ns = "com.springbaic2.mappers.memberMapper";
	@Inject
	private SqlSession ses; // sqlSessionTemplate 객체 주입

	@Override
	public String getDateTime() {
		String sql = "com.springbaic2.mappers.memberMapper.getDateTime";

		// 단일행인 결과값을 가져온다.
		return this.ses.selectOne(sql);
	}

	@Override
	public int insertMember(MemberVO dto) throws NamingException, SQLException {
		String sql = "com.springbaic2.mappers.memberMapper.insertMemberWithFile";
		return this.ses.insert(sql, dto);
	}

	@Override
	public int updateMember(MemberVO dto) throws Exception {
		// 전역변수에 쿼리문의 공통되는 부분을 선언 해놓고 이렇게 쓰는 것이 좀 더 효율 적이다.
		return this.ses.update(ns + ".updateMember", dto);
	}

	@Override
	public int deleteMember(String userId) throws Exception {

		return this.ses.delete(ns + ".deleteMember");
	}

	@Override
	public MemberVO viewMemberByUserId(String userId) throws Exception {

		return this.ses.selectOne(ns + ".viewMemberByUserId", userId);
	}

	@Override
	public List<MemberVO> viewAllMember() throws Exception {

		return this.ses.selectList(ns + ".viewAllMember");
	}

	@Override
	public MemberVO loginMember(String userId, String userPwd) throws Exception {

		//SqlSessionTemplate 객체의 insert,update,delete, select 문의 파라메터는 
		//Object로 하나만 받을 수 있다.
		
		//여러개의 파라메터 값을 Map으로 감싸서 넘겨 줄 수 있다.
		
		// dto를 안만든다고 하면 이렇게 쓸 수도 있다.
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("userPwd", userPwd);
		return this.ses.selectOne(ns + ".loginMember", param);
	}

	@Override
	public int checkDuplicateByUserId(String userId) throws Exception {
		
		return this.ses.selectOne(ns + ".checkDuplicateByUserId", userId);
	}

	
	@Override
	public List<MemberPointVO> selectAllPointListByUserId(String userId) throws Exception {
		
		return this.ses.selectList(ns +".selectAllPointListByUserId",userId);
	}

}
