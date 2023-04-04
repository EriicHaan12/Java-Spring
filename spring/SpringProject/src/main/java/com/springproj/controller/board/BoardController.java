package com.springproj.controller.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //현재 클래스가 컨트롤러 단임을 어노테이션을 통해 선언
@RequestMapping("/board/*") // /board/ 로 들어오는 모든 request를 여기에서 매핑하겠다는 뜻
public class BoardController {
	
	@RequestMapping("listAll")
	public void listAll() {
		System.out.println("게시판 목록 조회");
	}
}
