package com.springproj.persistence;

import java.util.List;

import com.springproj.domain.BoardImg;
import com.springproj.domain.BoardVo;
import com.springproj.domain.MemberPointVo;
import com.springproj.etc.UploadFileInfo;

public interface BoardDAO {
	//게시판 전체 목록 조회
	List<BoardVo> selectAllBoard()throws Exception;
	
	//신규 게시물 저장(파일은 제외한 나머지 데이터)
	int insertNewBoard(BoardVo newBoard)throws Exception;
	
	//최근 insert된 게시물의 글 번호 가져오기
	int selectRecentBoardNo()throws Exception;
	
	//게시물에 파일이 있다면 파일 insert
	int insertBoardFile(int boardNo, UploadFileInfo ufi)throws Exception;
	
	//게시물 작성한 회원에게 포인트 부여
	int addPointToMember(MemberPointVo mpv)throws Exception;
	
	//게시물 no번 클릭시 조회수 증가
	int updateReadCount(int no)throws Exception;
	
	//no번 게시글 조회
	BoardVo selectByBoardNo(int no)throws Exception;

	//no번 첨부 파일 조회
	List<BoardImg> selectUploadFile(int no)throws Exception;
	
	//no번 게시물 삭제
	int deleteBoardByNo (int no)throws Exception;
	
}
