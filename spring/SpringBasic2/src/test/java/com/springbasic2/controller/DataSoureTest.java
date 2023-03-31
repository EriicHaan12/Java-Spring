package com.springbasic2.controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//root-context.xml에 있는 Connection 객체가 잘 주입되는지 테스트 해보자

//이 클래스가 `JUnit과 함께 동작한다`라고 설정 하기 위해 쓰는것
// 현재 클래스를 실행 시킬 때 Spring이 함께 로딩되도록(그래야 root-context를 읽어 올 수 있기 때문)
@RunWith(SpringJUnit4ClassRunner.class)
//현재 DataSoureTest와 함께 구동시킬 라이브러리 클래스 설정
//root-context.xml의 설정을 얻어오기 위해 씀
@ContextConfiguration(locations =
//root-contexl 파일 위치 설정
{ "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" }

)

public class DataSoureTest {

	// Inject: 객체 주입 root-context 파일내에 DataSource라는 객체를 Spring에서 알아서 찾아
	// ds에 주입 해준다
	
	
	//Spring container(root-context.xml) 안에 지정한 맴버 클래스를 Inject로 지정해주면
	// Spring에서 알아서 같은 이름의 객체를 찾아 맴버에 가지고 있는 데이터를 주입한다.
	// 제어 역행(IoC : Inversion of Control),의존성 주입(DI : Dependency Injection) 특성
	// 객체 주입(root-context.xml에 있는 bean 객체 중에 데이터 타입이 DataSoure(지정한 맴버와 이름이 같은)인 객체 주입)
	@Inject 
	private DataSource ds;

	// 테스트 대상 메서드 지정
	@Test
	public void testConnection() throws ClassNotFoundException {

//	root-context에 있는 DataSource를 가져와 ds객체를 생성 했고, ds에는 이미 url,username,password를 
// 이미 가지고 있기 때문에 따로 parameter로 주지 않아도 된다.
		try (Connection con = ds.getConnection()) { // 예외가 발생 하지 않았을 때
			System.out.println(con.toString());
		} catch (SQLException s) { // 예외가 발생 했을 때
			s.printStackTrace();
		}
	}

}
