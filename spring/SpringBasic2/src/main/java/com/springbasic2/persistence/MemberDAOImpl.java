package com.springbasic2.persistence;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.springbasic2.domain.MemberVO;

@Repository // 현재 클래스가 DAO단임을 Spring컨테이너에게 알린다.
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession ses; // sqlSessionTemplate 객체 주입
	
	@Override
	public String getDateTime() {
		String sql = "com.springbaic2.mapper.memberMapper.getDateTime";
		
		// 단일행인 결과값을 가져온다.
		return this.ses.selectOne(sql);
	}

	@Override
	public int insertMember(MemberVO dto) throws NamingException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
