package com.springproj.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.springproj.domain.MessageVo;
import com.springproj.persistence.MessageDAO;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Inject
	private MessageDAO dao;
	
	@Override
	public boolean send(MessageVo msg) throws Exception {
		
		int result = dao.sendMessage(msg);
		if(result ==1) {
			return true;
		}
		
		return false;
	}

	@Override
	public List<MessageVo> getAllMsg(String userId) throws Exception  {
		
		List<MessageVo> lst = dao.selectAllMsg(userId);
		
		//읽은 쪽지를 "읽음" 처리로 하기
		for(MessageVo msg : lst) {	
		
			 dao.updateMsgReadProc(msg.getMsgId());
		}
		return lst;
	}

	@Override
	public int getMsgCnt(String userId) throws Exception {
		
		return dao.selectMsgCnt(userId);
	}

}
