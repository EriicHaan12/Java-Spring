package com.springproj.controller.board;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springproj.domain.BoardVo;
import com.springproj.service.BoardService;

@Controller // 현재 클래스가 컨트롤러 단임을 어노테이션을 통해 선언
@RequestMapping("/board/*") // /board/ 로 들어오는 모든 request를 여기에서 매핑하겠다는 뜻
public class BoardController {

	@Inject
	private BoardService service;

	@RequestMapping("listAll")
	@ExceptionHandler()
	public void listAll(Model model) throws Exception {
		System.out.println("controller : 게시판 목록 조회");
		
			List<BoardVo> lst = this.service.listAll();

			model.addAttribute("boardList", lst);

		
	}
	
	// SQLException이 발생하면 아래의 메서드가 호출
	
	@ExceptionHandler(SQLException.class)// 이 예외가 발생됬을 때 호출되는 메서드
	public String sqlExceptionHandling(SQLException se) {
		System.out.println("SQL Exception 발생!");
		
		System.out.println(se.getMessage());
		se.printStackTrace();
		return null;
	}
	// ArithmeticException이 발생하면 아래의 메서드가 호출
	public String arithmeticExceptionHandiling(ArithmeticException ae) {
		System.out.println(ae.getMessage());
		return null;
	}
}
