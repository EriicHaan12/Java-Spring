package com.springproj.persistence;

import java.util.List;

import com.springproj.domain.MessageVo;

public interface MessageDAO {
	
	//메세지 발송
	int sendMessage(MessageVo msg)throws Exception;

	//한 유저가 받은 쪽지 모두 호출
	List<MessageVo> selectAllMsg(String userId)throws Exception;
	
	//쪽지 읽음 처리
	void updateMsgReadProc(int msgId)throws Exception;

	//로그인한 유저가 읽지 않은 쪽지 갯수 가져오기
	int selectMsgCnt(String userId)throws Exception;

}
