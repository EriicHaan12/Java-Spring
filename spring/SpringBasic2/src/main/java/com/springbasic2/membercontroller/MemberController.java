package com.springbasic2.membercontroller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller    // 현재 클래스가 컨트롤러 단 임을 명시
public class MemberController {
	
	
	public MemberController() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 로그를 남길 수 있도록 하는 객체
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	//RequestMapgging = servlet 단으로 부터 요청된 객체를 매핑
	//"/"가 GET방식으로 요청되면 아래의 home 메서드를 호출하게 된다.
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		// Model 객체에 "serverTime"이라는 이름으로 formattedDate를 바인딩
		//Model 객체(Controller 단에서 View단으로 바인딩된 정보를 넘겨주는 객체)
		model.addAttribute("serverTime", formattedDate ); 
		
		
		System.out.println("Hello,world");
		
		return "home"; // servlet에게 "home"문자열 반환
		
		//servlet-context에 설정되어 있는  servlet 객체의 ViewReslover에게 return 되고, 
		
		/*
		<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
			<beans:property name="prefix" value="/WEB-INF/views/" />
			<beans:property name="suffix" value=".jsp" />
		</beans:bean>
	*/
		// 위의 ViewResolver 객체에 의해 forwarding되거나 이동될 VIew페이지의 이름과 경로가
		// 조립된다. 
		// "/WEB-INF/views/"+ "home"+ +".jsp"
		//그래서  /WEB-INF/views/home.jsp로 포워딩 
	}
	
	//이렇게만 넣어주면 requestMethod가 자동으로 get 방식으로 지정되고(default값이 get)
	//value가 memberView가 된다.
	@RequestMapping("memberView")
	public String doMemberView(){
		System.out.println("doMemberView 호출");
		return "doMemberView"; // WEB-INF/views/doMemberView.jsp로 응답
	}
	
	
	
	//value 로 오는 게 mapping 주소

		@RequestMapping(value = "memberModi", method = RequestMethod.GET)
		public String doMemberModify() {
		
			System.out.println("doMemberModify 호출됨!");
			return "memberModify";
		
		}
		//매핑되는 매핑주소를 여러개로 지정 할 수 있다.
		// value로 기입한 둘중 아무거나 호출 해도 매핑주소로 가진다.
		@RequestMapping(value= {"memberSave","memberInput"}, method=RequestMethod.GET)
		public String doMemberSave() {
			//Servlet-context설정에 의해서
			//return으로 준 이름의 파일을 알아서 찾아간다.
			//즉 memberModi.jsp 파일을 호출 한다
			return "memberSave";	
		}
		
		//return 타입이 void인 경우에는 매핑 주소.jsp를 현재 경로에서 찾게 된다.
		@RequestMapping(value ="logout")
		public void doLogout() {
			System.out.println("logout 호출됨");

		// logout.jsp를 찾아 response 
		}
		
		
		
		
		
		@RequestMapping("hello")
		//@ModelAttribute : 해당 파라메터를 변수에 저장했다가, view까지 전달
		
		//이후 쿼리스트링에 저장된 해당 파라메터를 찾아 쿼리스트링 값을 가져온다.
		//req.getparameter 역할을 해준다.
		// 해당 파라메터가 null 가능 (에러 안남)
		
		public String doHello(@ModelAttribute("name")String name, @ModelAttribute("age")String age) {
		int intAge = -1;
		try {
			 intAge = Integer.parseInt(age);	
		}catch(NumberFormatException e) {
			intAge= 0;
		}
			System.out.println("name : " + name);
			System.out.println("age : " + intAge);
		
			return "sayHello";
		}
	
}
