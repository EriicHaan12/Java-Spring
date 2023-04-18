package com.springproj.aop;


import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component // Spring 컨테이너에 bean으로 자동 등록 시키기 위한 어노테이션
@Aspect // AOP 기능을 하는 클래스를 선언 할 때 추가하는 어노테이션
public class ExampleAdvice {
	//현재 클래스에서 로그를 남기기위한 로그 객체를 얻어옴
	private static final Logger logger= LoggerFactory.getLogger(ExampleAdvice.class);
	
	//아래의 startLog()는 com.springproj.service.BoardServiceImpl클래스의 매개변수가 0개 이상인 모든 메서드가 
	// 실행되기 이전(before)에 실행되도록 한다.
	@Before("execution(* com.springproj.service.BoardServiceImpl.saveBoard(..))")
	public void startLog(JoinPoint joinPoint) {
		this.logger.info("------------------------------------");
		this.logger.info("start log");
		this.logger.info("------------------------------------");
		
		
		System.out.println("------------------------------------");
		System.out.println("start log");
		System.out.println("------------------------------------");
		
		System.out.println(Arrays.toString(joinPoint.getArgs()));
	}
	
	// com.springproj.service.ReplyServiceImpl.addPeply()가 실행되기 이전과 이후에 개입
	@Around("execution(* com.springproj.service.ReplyServiceImpl.addPeply(..))")
	public Object timeLog(ProceedingJoinPoint pJoinPoint) throws Throwable {
		//addReply(..)가 호출되기 이전에 수행 할 것
		System.out.println("------------------------------------");
		System.out.println("start log");
		System.out.println("------------------------------------");
	
		long startTime= System.currentTimeMillis();
		System.out.println("댓글 저장 시작 시간 : " + startTime);
		System.out.println("매개변수 : " + Arrays.toString(pJoinPoint.getArgs()));
		
		//addpeply() 메서드 실행되도록 처리한다.
		//proceed() 의 반환값은  addReplu()의 반환값 타임
			Object result =  pJoinPoint.proceed();
			
			// addReply() 호출 된 이후에 수행할 것 들을 기술
			long endTime= System.currentTimeMillis();
			System.out.println("댓글 저장 끝난 시간 : " + endTime);
			System.out.println("댓글 저장 수행 시간 : " + (endTime-startTime));
			
			 // 여기에서는  addReply()를 호출하는 반환값(boolean)을 service.addReply() 를 호출한 곳으로 반환 해준다.
			
			return result;  //return으로 받은 값들은 일반 메서드의 원래 가던 순서대로 가지게 된다.
	}
}
