package com.springproj.controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.springproj.domain.BoardImg;
import com.springproj.domain.BoardLikeDTO;
import com.springproj.domain.BoardVo;
import com.springproj.domain.MemberVo;
import com.springproj.domain.PagingInfo;
import com.springproj.domain.SearchCriteria;
import com.springproj.etc.UploadFileInfo;
import com.springproj.etc.UploadFilesProc;
import com.springproj.service.BoardService;

@Controller // 현재 클래스가 컨트롤러 단임을 어노테이션을 통해 선언
@RequestMapping("/board/*") // /board/ 로 들어오는 모든 request를 여기에서 매핑하겠다는 뜻
public class BoardController {

	@Inject
	private BoardService service;

	private String realPath;

	// 신규 업로드된 파일의 리스트(게시판 저장시 사용될 파일 리스트)
	private List<UploadFileInfo> upFileList = new ArrayList<UploadFileInfo>();

	@RequestMapping("listAll")
	// defaultValue 를 주게 되면 쿼리스트링을 받지 않더라도 defalut 값으로 pageNo=1이 param 값으로 생성 된다.
	public void listAll(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "viewPost", defaultValue = "3") int viewPostCnt,
			@RequestParam(value = "searchType", defaultValue = "") String searchType,
			@RequestParam(value = "searchWord", defaultValue = "") String searchWord) throws Exception {

		SearchCriteria sc = new SearchCriteria(searchType, searchWord);

		System.out.println("검색 : " + sc.toString());

		System.out.println("controller : 게시판 목록 조회, 페이지 번호 : " + pageNo + ", 보여줄 글의 갯수 : " + viewPostCnt);

		Map<String, Object> map = this.service.listAll(pageNo, viewPostCnt, sc);

		model.addAttribute("boardList", (List<BoardVo>) map.get("boardList"));
		model.addAttribute("pagingInfo", (PagingInfo) map.get("pagingInfo"));

	}

	// 호출될 땐 POST 방식 보다 GET 방식이 먼저 호출 된다.
	// 글 쓰러 들어올 때 호출
	@RequestMapping("writeBoard")
	public void writeBoard() {
		System.out.println("controller : 게시판 글쓰기");
		// writeBoard.jsp를 호출
	}

	// 글쓰기 완료 버튼을 눌렀을 때
	@RequestMapping(value = "writeBoard", method = RequestMethod.POST)
	public String writeBoard(BoardVo newBoard) throws Exception {
		System.out.println("controller : 게시판 저장");
		System.out.println(newBoard.toString());
		String redirectPage = "";
		if (this.service.saveBoard(newBoard, upFileList)) {// 업로드파일이 있다면
			if (this.upFileList.size() > 0) {
				this.upFileList.clear();// 글등록이 완료 되면 해당 글의 업로드 파일리스트를 비워 준다.

				redirectPage = "listAll";
				// redirectPage="/board/listAll";
			} else {
				redirectPage = "writeBoard?status=fail";
				// redirectPage="/board/writeBoard?status=fail";
			}
		}
		;
		return "redirect:" + redirectPage; // 게시글 성공 혹은 실패시 갈 redirect 페이지 설정
	}

	@RequestMapping(value = "upfiles", method = RequestMethod.POST) // post 방식으로 처리
	public @ResponseBody UploadFileInfo uploadFileProcess(MultipartFile upfiles, HttpServletRequest req)
			throws IOException {
		// request로 받아온 file을 불러낼 클래스
		System.out.println("파일 업로드 처리");
		System.out.println("업로드 된 파일 이름 : " + upfiles.getOriginalFilename());
		// getName() 객체명, getOriginalFilename 객체 내 파일 명
		// post 방식으로 밖에 작동 되지 않는다.
		System.out.println("업로드된 파일 타입 : " + upfiles.getContentType());

		String originFilename = upfiles.getOriginalFilename();
		String originFileType = upfiles.getContentType();
		byte[] upfilesContent = upfiles.getBytes(); // 실제 파일의 내용

		System.out.println("업로드된 파일 내용 : " + upfilesContent);

		this.realPath = req.getSession().getServletContext().getRealPath("resources/upfiles");
		// request로부터 받아온 servlet 객체를 저장 할 경로(저장될 물리적 경로)
		// 현재실행중인session 얻어오고 -> Session에서 실행중인 Context를 얻어온 뒤 -> 실질적인 경로를 얻어온다.

		UploadFileInfo fileInfo = UploadFilesProc.uploadFile(originFilename, originFileType, upfilesContent, realPath);

		if (fileInfo != null) {

			this.upFileList.add(fileInfo);
			System.out.println("업로드된 파일 리스트 사이즈 : " + upFileList.size());
			System.out.println("업로드된 파일 리스트 : " + fileInfo);
		}
		for (UploadFileInfo ufi : this.upFileList) {
			System.out.println("파일 업로드 리스트 : " + ufi.toString());
		}

		return fileInfo;
	}

	@RequestMapping(value = "remfile")
	public ResponseEntity<String> removeFile(@RequestParam("remFileName") String delFileName) {

		System.out.println("삭제할 파일명 : " + delFileName);

		int indexOfDeletedFile = 0; // 삭제되는 파일 리스트의 인덱스 번호를 확인하기 위해서
		// 게시판 글 수정시 파일 삭제
		for (UploadFileInfo ufi : upFileList) {
			if (delFileName.equals(ufi.getOriginFileName())) { // 삭제할 파일 찾기
				UploadFilesProc.deleteUpFile(ufi, delFileName);// 삭제 하기
				break;
			}
			indexOfDeletedFile++;
		}
		this.upFileList.remove(indexOfDeletedFile); // 리스트에서 파일 삭제
		for (UploadFileInfo ufi : this.upFileList) {
			System.out.println("현재 파일 업로드 리스트 : " + ufi.toString());
		}
		ResponseEntity<String> result = new ResponseEntity<String>("success", HttpStatus.OK);
		return result;
	}

	@RequestMapping("viewBoard")
	public void viewBoard(@RequestParam("no") int no, Model model) throws Exception {
		System.out.println(no + "번 글 조회!");
		Map<String, Object> map = this.service.viewByBoardNo(no);

		// 리턴된 Map으로 부터 다시 원래 객체를 얻어옴
		BoardVo board = (BoardVo) map.get("board");
		List<BoardImg> lst = (List<BoardImg>) map.get("upFiles");
		List<BoardLikeDTO> listList = (List<BoardLikeDTO>) map.get("likeList");

		// 바인딩
		model.addAttribute("board", board);
		model.addAttribute("upFiles", lst);
		model.addAttribute("listList", listList);
	}

	@RequestMapping("modiBoard")
	public void modiBoard(@RequestParam("no") int no, @RequestParam("writer") String writer, Model model)
			throws Exception {

		System.out.println(no + "번을 DB에서 가져오기");
		// DB다녀옴(no번 글+ no번 글의 첨부 파일을 같이 얻어옴)
		// 리턴된 Map으로 부터 다시 원래 객체를 얻어옴
		Map<String, Object> map = this.service.viewByBoardNo(no);

		BoardVo board = (BoardVo) map.get("board");
		board.setContent(board.getContent().replace("<br />", "\n"));

		List<BoardImg> lst = (List<BoardImg>) map.get("upFiles");

		// 바인딩
		model.addAttribute("board", board);
		model.addAttribute("upFiles", lst);

		// List<BoardImg>에 있는 객체들을 List<UploadFileInfo> upFileList 로 바꾸어 저장
		this.upFileList.clear();

		for (BoardImg bi : lst) {
			this.upFileList.add(new UploadFileInfo(bi));
		}

		for (UploadFileInfo ufi : this.upFileList) {
			System.out.println(" 수정 바인딩한  업로드 리스트 : " + ufi.toString());
		}

	}

	@RequestMapping(value = "modifyBoard", method = RequestMethod.POST)
	public String modifyBoard(BoardVo modifyBoard) throws Exception {
		System.out.println(modifyBoard.toString() + "로 수정 하자");

		this.service.modifyBoard(modifyBoard, this.upFileList);

		this.upFileList.clear();

		return "redirect:viewBoard?no=" + modifyBoard.getNo();
	}

	@RequestMapping("remBoard")
	public String deleteBoardByNo(@RequestParam("no") int no) throws Exception {
		System.out.println("삭제할 게시글 번호 : " + no);

		int result = service.deleteBoardByNo(no);

		System.out.println("삭제할 게시글 번호 : " + no);

		System.out.println(result);

		String redirectPage = "";
		if (result == 1) {
			redirectPage = "/board/listAll";
		} else {
			redirectPage = "/board/viewBoard?no=" + no;
		}
		return "redirect:" + redirectPage;

	}

	@RequestMapping(value = "like", method = RequestMethod.POST)
	public ResponseEntity<String> likeBoard(@RequestParam("isLike") boolean isLike,
			@RequestParam("boardNo") int boardNo, @RequestParam("who") String who) {

		System.out.println(boardNo + "번 글을 좋아요 한 수 : " + isLike);

		BoardLikeDTO dto = new BoardLikeDTO(0, who, boardNo, isLike);
		ResponseEntity<String> result = null;

		try {
			int likeCount = service.likeProc(dto);
			result = new ResponseEntity<String>(likeCount + "", HttpStatus.OK);
		} catch (Exception e) {
			result = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}

		return result;
	}

}
