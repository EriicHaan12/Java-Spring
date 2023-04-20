package com.springproj.persistence;

import java.util.List;

import com.springproj.domain.BoardImg;
import com.springproj.domain.BoardLikeDTO;
import com.springproj.domain.BoardVo;
import com.springproj.domain.MemberPointVo;
import com.springproj.domain.PagingInfo;
import com.springproj.domain.SearchCriteria;
import com.springproj.etc.UploadFileInfo;

public interface BoardDAO {
	//게시판 전체 목록 조회
	List<BoardVo> selectAllBoard(PagingInfo pi)throws Exception;
	
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
	
//	//no번 게시물 삭제
	int deleteBoardByNo (int no)throws Exception;
	
	//no번 게시물 수정
	int updateBoard(BoardVo modiBoard)throws Exception;
	
	//no첨부 파일 삭제
	int deleteBoardImg(int no)throws Exception;

	//게시판 글의 갯수 가져오기
	int getBoardCnt()throws Exception;

	//검색어가 있을 때 검색된 게시글의 글 갯수 가져오기
	int boardCntWithSearch(SearchCriteria sc)throws Exception;

	//검색어가 있을 때 검색된 글을 페이징 하여 가저오기
	List<BoardVo> selectallBoardWithSearch(PagingInfo pi, SearchCriteria sc)throws Exception;

	//좋아요 클릭시 처리
	int insertBoardLike(BoardLikeDTO dto)throws Exception;

	//좋아요 클릭 후 다시 누를 때
	int deleteBoardLike(BoardLikeDTO dto)throws Exception;

	//Like/disLike 처리시 likeCount 증감
	int addLikeCount(int acc, int boardNo)throws Exception;

	//boardNo번그의 좋아요 갯수 가져오기
	int getLikeCountByBoardNo(int boardNo)throws Exception;

	//no번글을 좋아요 한 유저 리스트 가져오기
	List<BoardLikeDTO> getLikeList(int no)throws Exception;

	// 모든 첨부파일 가져오기
	List<BoardImg> selectAllBoardImg()throws Exception;


}
