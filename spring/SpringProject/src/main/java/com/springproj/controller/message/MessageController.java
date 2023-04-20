package com.springproj.controller.message;

import java.net.http.HttpRequest;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springproj.domain.MemberVo;
import com.springproj.domain.MessageVo;
import com.springproj.service.MessageService;

@RestController
@RequestMapping("/message/*")
public class MessageController {

	@Inject
	private MessageService service;

	@RequestMapping(value = "send", method = RequestMethod.POST)
	public ResponseEntity<String> sendMessage(@RequestBody MessageVo msg, HttpServletRequest req) {

		ResponseEntity<String> result = null;

		MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");

		// 로그인한 유저 아이디를 얻어 sender에 등록
		String sender = loginMember.getUserId();

		System.out.println(msg.toString() + "를 보내자");

		msg.setSender(sender);
		try {
			if (service.send(msg)) {
				result = new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			result = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);

		}
		return result;
	}

	@RequestMapping("view/{userId}")
	public ResponseEntity<List<MessageVo>> getAllMsg(@PathVariable("userId") String userId) throws Exception {
	
		

		System.out.println(userId + "에게 온 쪽지 가져오기");
		ResponseEntity<List<MessageVo>> result = null;
		try {
			List<MessageVo>	lst = service.getAllMsg(userId);
			result = new ResponseEntity<List<MessageVo>>(lst, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return result;
	}
	@RequestMapping("updateMsgCnt/{userId}")
	public ResponseEntity<String> getMsgCnt(@PathVariable("userId")String userId) {
		
		ResponseEntity<String>result = null;
		
		try {
			int cnt = service.getMsgCnt(userId);
			result = new ResponseEntity<String>(cnt+"",HttpStatus.OK);
		} catch (Exception e) {
			result = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return result;
	}
}
