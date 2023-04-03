package com.springbasic2.persistence;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.springbasic2.domain.MemberPointVO;
import com.springbasic2.domain.MemberVO;

public interface MemberDAO {
//DB로부터 현재날짜와 현재 시간을 얻어오는 	
String getDateTime();

//회원 가입
int insertMember(MemberVO dto) throws Exception;

//회원 수정
int updateMember(MemberVO dto)throws Exception;

//회원 삭제
int deleteMember(String userId)throws Exception;

//유저 아이디로 회원조회
MemberVO viewMemberByUserId(String userId)throws Exception;

// 전체 회원 조회
List<MemberVO>viewAllMember()throws Exception;

//로그인
MemberVO loginMember(String userId, String userPwd)throws Exception;


//아이디 중복 검사
int checkDuplicateByUserId(String userId)throws Exception;

//유저 아이디로 한 유저의 포인트 내역 조회
List<MemberPointVO>selectAllPointListByUserId(String userId)throws Exception;


}


