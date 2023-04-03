package com.springbasic2.test.persistence;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springbasic2.domain.MemberPointVO;
import com.springbasic2.domain.MemberVO;
import com.springbasic2.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class MemberDAOTest {
	@Inject
	private MemberDAO dao;// MemeberDAO 객체 주입

//	@Test
//	public void getDateTimeTest() {
//		 System.out.println( this.dao.getDateTime()); 
//	}
	
//	@Test
//	public void inserMemberTest() {
//		MemberVO member = new MemberVO("test", "1234", "test@t.com", "010-1244-1567", "m", null, null, null, "memo",
//				"Y");
//		
//		System.out.println(member.toString());
//		try {
//			if (this.dao.insertMember(member) == 1) {
//				System.out.println("회원 저장 성공!");
//			} else {
//				System.out.println("실패!");
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void updateMemberTest() throws Exception {
//		MemberVO member = new MemberVO("test", null,null, null, null, null, null, "uploadMember/lego(2).jpg",null,
//				null);
//
//	
//		if(dao.updateMember(member)==1) {
//			 System.out.println("회원 수정 성공");
//		 }
//	}
//	@Test
//	public void deleteMemberTest()throws Exception {
//		String userId = "test";
//		if(dao.deleteMember(userId)==1) {
//			System.out.println("회원 삭제 성공");
//		}
//	}

//	@Test
//	public void viewMemberByUserId()throws Exception {
//		String userId = "hanoo2";
//		System.out.println(dao.viewMemberByUserId(userId).toString());
//	}
	
//	@Test
//	public void viewAllMember()throws Exception {	
//		for (MemberVO member : dao.viewAllMember()) {
//			System.out.println(dao.viewAllMember().toString());
//		}		
//	}
//	@Test
//	public void viewAllMember()throws Exception {
//		if(dao.loginMember("hanoo2","1234")!=null) {
//			System.out.println("로그인 성공");
//		}else {
//			System.out.println("로그인 실패");
//		}
//	}
//	@Test
//	public void checkDuplicateByUserId()throws Exception {
//		String userId = "hanoo2";
//		System.out.println(dao.checkDuplicateByUserId(userId));
//		if(this.dao.checkDuplicateByUserId(userId)!=0) {
//			System.out.println("중복된 아이디 입니다.");
//		}else {
//			System.out.println("사용 가능한 아이디 입니다.");
//		}
//	}
	
	@Test
	public void selectAllPointListByUserId()throws Exception {
		String userId = "hanoo2";
		for (MemberPointVO mp : dao.selectAllPointListByUserId(userId)) {
			System.out.println(dao.selectAllPointListByUserId(userId).toString());
			
		}
		
	
	}
	
	
	
	
	
}
