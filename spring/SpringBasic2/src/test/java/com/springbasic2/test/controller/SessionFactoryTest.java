package com.springbasic2.test.controller;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class SessionFactoryTest {

	@Inject
	private SqlSessionFactory ssf;

	@Test
	public void sqlSessionFactoryTest() {
		//sql에서 Session 객체 얻어오기 위한 클래스
		//.openSession() 가져온 Session을 보여주기위한 메서드(객체 가져오기 위해 씀) 
		try (SqlSession ss = ssf.openSession()) {
			System.out.println(ss.toString());
		} catch (Exception s) {
			s.printStackTrace();
		}

	}

}
