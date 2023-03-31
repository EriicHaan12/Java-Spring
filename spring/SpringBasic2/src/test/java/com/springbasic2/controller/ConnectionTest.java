package com.springbasic2.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class ConnectionTest {
	private static String driverClassName = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/hjw?useSSL=false&serverTimezone=Asia/Seoul&characterEncoding=UTF-8";
// xml과는 다르게 & 인식이 되기 때문에 &amp; 부분을 고쳐줘야함

	private static String username = "root";
	private static String password = "1234";

// 아래의 메서드가 JUnit4 라이브러리에 의해 동작되는 테스트 메서드 임을 알림
// 또한 쓰기 위해서는 pom 파일의 버전을 확인 한 뒤 라이브러리를 import 해야됨
	@Test
	public void testConnection() throws ClassNotFoundException {
		Class.forName(driverClassName);

		try (Connection con = DriverManager.getConnection(url, username, password)) { // 예외가 발생 하지 않았을 때
			System.out.println(con.toString());
		} catch (SQLException s) { // 예외가 발생 했을 때

			s.printStackTrace();
		}
	}

}
