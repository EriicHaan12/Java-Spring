package com.springproj.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springproj.domain.BoardImg;
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
	// 트랜잭션 어노테이션 사용(다른 설정을 추가 하지 않으면 default(기본격리 수준으로)로 설정됨 )
	@Transactional
	public boolean saveBoard(BoardVo newBoard, List<UploadFileInfo> fileList) throws Exception {

		// 1~4번 전체를 트랜잭션 처리 해줘야함

		// 1) 글 insert
		// 게시글 줄바꿈 처리
		newBoard.setContent(newBoard.getContent().replace("\n", "<br />"));
		int insertResult = dao.insertNewBoard(newBoard);

		if (insertResult == 1) {
			// 2) 1번에서 insert된 글의 글번호 가져오기
			int boardNo = dao.selectRecentBoardNo();

			// 3) 2번에서 얻어온 글번호로 파일이 있다면 파일 insert
			for (UploadFileInfo ufi : fileList) {
				dao.insertBoardFile(boardNo, ufi);
			}

			// 4)글쓴 맴버에게 포인트 부여
			dao.addPointToMember(new MemberPointVo(0, newBoard.getWriter(), null, "게시판글쓰기", 0));

		}
		return true;
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED) // 커밋된 데이터만 읽기

	public Map<String, Object> viewByBoardNo(int no) throws Exception {

		// 게시글 조회시 조회수 늘리기 작업(이번엔 24시간 제약 걸지말고 누를 때마다 올리기)

		// 1) no번 글의 조회수 증가
		dao.updateReadCount(no);

		// 2) DB 접근해서 no번 글 읽어오기
		BoardVo board = dao.selectByBoardNo(no);
		// 3) 첨부 파일 읽어옴

		List<BoardImg> upFiles = dao.selectUploadFile(no);

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("board", board);
		returnMap.put("upFiles", upFiles);

		return returnMap;
	}

	@Override
	public int deleteBoard(int no) throws Exception {

		System.out.println("서비스단 삭제할 글번호 : " + no);

		return dao.deleteBoardImg(no);
	}

	@Override
	@Transactional
	public boolean modifyBoard(BoardVo modiBoard, List<UploadFileInfo> fileList) throws Exception {
		modiBoard.setContent(modiBoard.getContent().replace("\n", "<br />"));

		// 1) 게시물 update(작성일, 제목, content)
		int updateBoardResult = dao.updateBoard(modiBoard);

		if (updateBoardResult == 1) { // 뭔가 수정이 일어났을 때
			// 2) 업로드된 파일 update
			// -> no번 게시글 첨부파일 모두 삭제
			dao.deleteBoardImg(modiBoard.getNo());

			// ->업로드한 파일이 있다면 파일 insert
			for (UploadFileInfo ufi : fileList) {
				dao.insertBoardFile(modiBoard.getNo(), ufi);
			}

		}

		return true;
	}

}
