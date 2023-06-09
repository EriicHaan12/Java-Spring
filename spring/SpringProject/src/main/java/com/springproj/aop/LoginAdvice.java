package com.springproj.aop;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.springproj.domain.LoginDTO;
import com.springproj.domain.MemberVo;
import com.springproj.etc.GetClientIP;

@Component
@Aspect
public class LoginAdvice {

	@Around("execution(* com.springproj.service.MemberServiceImpl.login(..))")
	public Object saveLoginLog(ProceedingJoinPoint pjp) throws Throwable {

		System.out.println("Aspect 로그인 요청");

		LoginDTO dto = (LoginDTO) pjp.getArgs()[0];
		HttpServletRequest req = (HttpServletRequest) pjp.getArgs()[1];

		String tryLoginUser = dto.getUserId(); // 로그인을 시도하려는 유저
		String tryLoginIp = GetClientIP.getClientIp(req); // 로그인을 시도하려는 유저

		Timestamp ts = new Timestamp(System.currentTimeMillis());

		String now = ts.toString().substring(0, ts.toString().indexOf(" "));

		String savedRootPath = req.getSession().getServletContext().getRealPath("/resources/log");

		BufferedOutputStream bs = null;
		bs = new BufferedOutputStream(new FileOutputStream(new File(savedRootPath + File.separator + now + ".txt"),true));
		
		String savedText = ts.toString()+"t\t" + tryLoginUser + "가 로그인을 시도함";
		

		Object result = pjp.proceed();// login() 호출

		if((MemberVo)result!= null) {
			savedText += "\t\t" + "로그인 성공\n";
		}else {
			savedText += "\t\t" + "로그인 실패\n";
		}
		bs.write(savedText.getBytes());
		bs.close();
		
		return result;
	}
}
