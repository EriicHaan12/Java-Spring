package com.springproj.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // 현재 클래스가 공통 예외 처리 할 클래스 임을 명시
public class CommonException {
	// SQLException이 발생하면 아래의 메서드가 호출

//	@ExceptionHandler(SQLException.class) // 이 예외가 발생됬을 때 호출되는 메서드
//	public String sqlExceptionHandling(SQLException se) {
//		System.out.println("SQL Exception 발생!");
//
//		System.out.println(se.getMessage());
//		se.printStackTrace();
//		
//		return null;
//	}

	@ExceptionHandler(Exception.class) // 이 예외가 발생됬을 때 호출되는 메서드
	public String exceptionHandling(Exception e, Model model) {

		System.out.println("Exception 발생!");
		model.addAttribute("errorMsg", e.getMessage());
		model.addAttribute("stackTrace", e.getStackTrace());

		return "/error";
	}

	// ArithmeticException이 발생하면 아래의 메서드가 호출
	public String arithmeticExceptionHandiling(ArithmeticException ae) {
		System.out.println("ArithmeticException 발생!");
		System.out.println(ae.getMessage());
		return null;
	}

}
