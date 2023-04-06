package com.springproj.controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

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

import com.springproj.domain.BoardVo;
import com.springproj.etc.UploadFileInfo;
import com.springproj.etc.UploadFilesProc;
import com.springproj.service.BoardService;

@Controller // 현재 클래스가 컨트롤러 단임을 어노테이션을 통해 선언
@RequestMapping("/board/*") // /board/ 로 들어오는 모든 request를 여기에서 매핑하겠다는 뜻
public class BoardController {

	@Inject
	private BoardService service;
	
	private String realPath;

	// 업로드된 파일의 리스트
	private List<UploadFileInfo> upFileList = new ArrayList<UploadFileInfo>();

	@RequestMapping("listAll")
	public void listAll(Model model) throws Exception {
		System.out.println("controller : 게시판 목록 조회");

		List<BoardVo> lst = this.service.listAll();

		model.addAttribute("boardList", lst);
	}

	@RequestMapping("writeBoard")
	public void writeBoard() {
		System.out.println("controller : 게시판 글쓰기");
		// writeBoard.jsp를 호출
	}

	@RequestMapping(value = "writeBoard", method = RequestMethod.POST)
	public void writeBoard(BoardVo newBoard) throws Exception {
		System.out.println("controller : 게시판 저장");
		System.out.println(newBoard.toString());

		if (this.service.saveBoard(newBoard, upFileList)) {

			if (this.upFileList.size() > 0) {
				this.upFileList.clear();// 글등록이 완료 되면 해당 글의 업로드 파일리스트를 비워 준다.
			}
		};
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
		for (UploadFileInfo ufi : upFileList) {

			if (delFileName.equals(ufi.getOriginFileName())) {// upFileList(업로드한 파일 리스트 중) 삭제할 파일을 찾았을 때
				UploadFilesProc.deleteUpFile(ufi, this.realPath); // 삭제 완료
				break;
			}
			indexOfDeletedFile++;
		}
		this.upFileList.remove(indexOfDeletedFile);// 리스트에서 파일삭제

		for (UploadFileInfo ufi : this.upFileList) {
			System.out.println("삭제 후 파일 업로드 리스트 : " + ufi.toString());
		}
		ResponseEntity<String> result = new ResponseEntity<String>("success", HttpStatus.OK);

		return result;
	}

}
