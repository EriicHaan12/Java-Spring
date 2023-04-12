package com.springproj.controller.reply;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springproj.domain.Replies;
import com.springproj.service.ReplyService;

@RestController // 현재 클래스가 REST방식으로 동작하는 컨트롤러 객체 임을 명시
@RequestMapping("/reply") // reply URI에 대해 현재 객체가 mapping
public class ReplyController {

	@Inject
	private ReplyService service;

	// @RequestBody => view단에서 Ajax를 통한 json 형태로 데이터를 넘겨주기 위해
	// 넘겨받을 Replies라는 Vo단의 형태로써 받겠다는 뜻
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> saveReply(@RequestBody Replies reply) {
		System.out.println(reply.toString() + "댓글 등록하자!");
		ResponseEntity<String> result = null;
		if (reply.getReplier().equals("")) {
			// 댓글 작성시 로그인을 안했을 경우
			result = new ResponseEntity<String>("notPermitted", HttpStatus.BAD_REQUEST);
		} else {
			// 댓글 등록 처리 후 긍정적인 result
			try {
				service.addReply(reply);
			} catch (Exception e) {
				result = new ResponseEntity<String>("notSaved", HttpStatus.CONFLICT);
				e.printStackTrace();
			}
			result = new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return result;
	}

	@RequestMapping(value = "/all/{boardNo}", method = RequestMethod.GET)
	public ResponseEntity<List<Replies>> getAllReplies(
			@PathVariable("boardNo") int boardNo) {
		ResponseEntity<List<Replies>> result =null;
		System.out.println(boardNo+ "번 글의 댓글을 전부 가져오기");
		
		try {
			List<Replies> lst =  service.getAllReplies(boardNo);
			//전체 댓글 리스트가 담겨져있는 lst를 반환하고 ,상태 코드 HttpStatus.OK도 같이 반환 
			result = new ResponseEntity<List<Replies>>(lst,HttpStatus.OK);
			
		} catch (Exception e) {
			
			//빈거라 내용이 없기 때문에, 보낼 메세지는 빼주고 상태 코드만 반환
	result = new ResponseEntity<List<Replies>>(HttpStatus.CONFLICT);
			
		}
		
		return 	result;
	}
}
