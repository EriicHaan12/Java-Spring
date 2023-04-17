package com.springproj.service;

import java.util.List;
import java.util.Map;

import com.springproj.domain.BoardLikeDTO;
import com.springproj.domain.BoardVo;
import com.springproj.domain.SearchCriteria;
import com.springproj.etc.UploadFileInfo;




public interface BoardService {
	//게시판 목록 조회
	Map<String,Object> listAll(int pageNo, int viewPostCnt, SearchCriteria sc) throws Exception;
	
	//신규 게시물 저장
	boolean saveBoard(BoardVo newBoard, List<UploadFileInfo> fileList)throws Exception;
	
	//게시물 상세 조회
	Map<String, Object>  viewByBoardNo(int no) throws Exception;
	
	//게시물 삭제
	int deleteBoard(int no) throws Exception;
	
	//게시물 수정
	boolean modifyBoard(BoardVo modiBoard, List<UploadFileInfo>fileList)throws Exception;
	
	//게시물 삭제
	int deleteBoardByNo(int no)throws Exception;

	//게시물 좋아요 처리

	int likeProc(BoardLikeDTO dto) throws Exception;
}
