package com.springproj.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.springproj.domain.Replies;
import com.springproj.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAO dao;

	@Override
	public boolean addReply(Replies reply) throws Exception {

		// 1)reply를 insert
		int result = dao.insertReply(reply);
		//
		if (result == 1) {
			return true;
		}

		return false;
	}

	@Override
	public List<Replies> getAllReplies(int boardNo) throws Exception {
		
		// boardNo번의 모든 댓글 가져오기
		List<Replies>lst =  dao.selectAllReplies(boardNo);
		return lst;
	}

	@Override
	public boolean modifyReply(Replies reply) throws Exception {
		int result =  dao.updateReply(reply);
		if(result ==1 ) {
			return true;
		}
		return false;
	}

}
