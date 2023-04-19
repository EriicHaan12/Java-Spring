package com.springproj.service;

import java.util.List;

import com.springproj.domain.MessageVo;

public interface MessageService {
	// 메세지 발송
	boolean send(MessageVo msg) throws Exception;

	//userId에게 도착한 모든 쪽지 가져오기
	List<MessageVo> getAllMsg(String userId)throws Exception;
}
