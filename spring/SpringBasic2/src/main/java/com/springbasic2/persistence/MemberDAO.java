package com.springbasic2.persistence;

import java.sql.SQLException;

import javax.naming.NamingException;

import com.springbasic2.domain.MemberVO;

public interface MemberDAO {
//DB로부터 현재날짜와 현재 시간을 얻어오는 	
String getDateTime();

//회원 가입
int insertMember(MemberVO dto) throws NamingException, SQLException;
}
