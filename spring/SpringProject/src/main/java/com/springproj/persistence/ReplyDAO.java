package com.springproj.persistence;

import java.util.List;

import com.springproj.domain.Replies;

public interface ReplyDAO {
	
	//댓글 달기
	int insertReply(Replies reply)throws Exception;

	List<Replies> selectAllReplies(int boardNo)throws Exception;
}
