package com.springproj.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.springproj.domain.BoardVo;
import com.springproj.domain.MemberPointVo;
import com.springproj.etc.UploadFileInfo;
import com.springproj.persistence.BoardDAO;

@Service // 현재 클래스가 서비스단임을 명시
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao; // BoardDAO 객체 주입

	@Override
	public List<BoardVo> listAll() throws Exception {
		System.out.println("서비스단: 게시판 목록 조회");

		return this.dao.selectAllBoard();
	}

	@Override
	public boolean saveBoard(BoardVo newBoard, List<UploadFileInfo> fileList) throws Exception {

		// 1) 글 insert
		int insertResult = dao.insertNewBoard(newBoard);

		// 2) 1번에서 insert된 글의 글번호 가져오기

		int boardNo = dao.selectRecentBoardNo();

		// 3) 2번에서 얻어온 글번호로 파일이 있다면 파일 insert
		for (UploadFileInfo ufi : fileList) {
			dao.insertBoardFile(boardNo, ufi);
		}

		// 4)글쓴 맴버에게 포인트 부여
		dao.addPointToMember(new MemberPointVo(0, newBoard.getWriter(), null, "게시판글쓰기", 0));

		return false;
	}

}
