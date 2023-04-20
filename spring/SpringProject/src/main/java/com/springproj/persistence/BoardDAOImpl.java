package com.springproj.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.springproj.domain.BoardImg;
import com.springproj.domain.BoardLikeDTO;
import com.springproj.domain.BoardVo;
import com.springproj.domain.MemberPointVo;
import com.springproj.domain.PagingInfo;
import com.springproj.domain.SearchCriteria;
import com.springproj.etc.UploadFileInfo;

@Repository // 현재 클래스가 DAO 단임을 명시
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession session; // SqlSessionTemplate 객체 주입

	private static String ns = "com.springproj.mappers.boardMapper";

	@Override
	public List<BoardVo> selectAllBoard(PagingInfo pi) throws Exception {
		System.out.println("DAO단 : 게시판 목록 조회");

		return session.selectList(ns + ".getAllBoards", pi);
	}

	@Override
	public int insertNewBoard(BoardVo newBoard) throws Exception {

		return session.insert(ns + ".saveNewBoard", newBoard);
	}

	@Override
	public int insertBoardFile(int boardNo, UploadFileInfo ufi) throws Exception {
		//
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("boardNo", boardNo);
		param.put("mimeType", ufi.getMimeType());
		param.put("ext", ufi.getExt());
		param.put("fileNameWithExt", ufi.getFileNameWithExt());
		param.put("thumbImgName", ufi.getThumbImgName());
		param.put("base64Str", ufi.getBase64Str());

		return session.insert(ns + ".saveUpFile", param);
	}

	@Override
	public int selectRecentBoardNo() throws Exception {

		return session.selectOne(ns + ".getRecentBoardNo");
	}

	@Override
	public int addPointToMember(MemberPointVo mpv) throws Exception {

		return session.insert(ns + ".addPointToMember", mpv);
	}

	@Override
	public int updateReadCount(int no) throws Exception {

		return session.update(ns + ".updateReadCount", no);
	}

	@Override
	public BoardVo selectByBoardNo(int no) throws Exception {

		return session.selectOne(ns + ".selectByBoardNo", no);
	}

	@Override
	public List<BoardImg> selectUploadFile(int no) throws Exception {

		return session.selectList(ns + ".selectUploadFile", no);
	}

	@Override
	public int updateBoard(BoardVo modiBoard) throws Exception {

		return session.update(ns + ".updateBoard", modiBoard);
	}

	@Override
	public int deleteBoardImg(int no) throws Exception {

		return session.delete(ns + ".deleteBoardImg", no);
	}

	@Override
	public int deleteBoardByNo(int no) throws Exception {
		System.out.println("DAO 단의 no : " + no);

		return session.delete(ns + ".deleteBoardByNo", no);
	}

	@Override
	public int getBoardCnt() throws Exception {

		return session.selectOne(ns + ".getTotalBoardCnt");
	}

	@Override
	public int boardCntWithSearch(SearchCriteria sc) throws Exception {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("searchType", sc.getSearchType());
		param.put("searchWord", "%" + sc.getSearchWord() + "%");

		return session.selectOne(ns + ".getTotalBoardCntWithSearch", param);
	}

	@Override
	public List<BoardVo> selectallBoardWithSearch(PagingInfo pi, SearchCriteria sc) throws Exception {

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("searchType", sc.getSearchType());
		param.put("searchWord", "%" + sc.getSearchWord() + "%");

		param.put("startRowIndex", pi.getStartRowIndex());
		param.put("viewPostCntPerPage", pi.getViewPostCntPerPage());

		return session.selectList(ns + ".getAllBoardWithCnt", param);
	}

	@Override
	public int insertBoardLike(BoardLikeDTO dto) throws Exception {

		return session.insert(ns + ".insertBoardLike", dto);
	}

	@Override
	public int deleteBoardLike(BoardLikeDTO dto) throws Exception {

		return session.delete(ns + ".deleteBoardLike", dto);
	}

	@Override
	public int addLikeCount(int boardNo, int acc) throws Exception {

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("boardNo", boardNo);
		param.put("acc", acc);

		return session.update(ns + ".updateLikeCount", param);

	}

	@Override
	public int getLikeCountByBoardNo(int boardNo) throws Exception {

		return session.selectOne(ns + ".getLikeCount", boardNo);
	}

	@Override
	public List<BoardLikeDTO> getLikeList(int no) throws Exception {

		return session.selectList(ns + ".getLikeList", no);
	}

	@Override
	public List<BoardImg> selectAllBoardImg() throws Exception {
	
		
		return session.selectList(ns+".getAllAppendFiles");
	}

}
