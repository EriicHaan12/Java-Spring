package com.springproj.service;

import java.util.List;

import com.springproj.domain.Replies;

public interface ReplyService {

	// 댓글 달기
boolean addReply(Replies reply)throws Exception;

//boardNo 번의 모든 댓글 조회
List<Replies> getAllReplies(int boardNo)throws Exception;

// reply로 댓글 수정
boolean modifyReply(Replies reply)throws Exception;
}
