package com.springproj.controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	//업로드된 파일의 리스트 
	private List<UploadFileInfo> upfileList = new ArrayList<UploadFileInfo>();
	
	@RequestMapping("listAll")
	public void listAll(Model model) throws Exception {
		System.out.println("controller : 게시판 목록 조회");

		List<BoardVo> lst = this.service.listAll();

		model.addAttribute("boardList", lst);
	}

	@RequestMapping("writeBoard")
	public void writeBoard() {
		System.out.println("controller : 게시판 글쓰기");

	}

	@RequestMapping(value = "upfiles", method = RequestMethod.POST) // post 방식으로 처리
	public @ResponseBody UploadFileInfo uploadFileProcess(MultipartFile upfiles, HttpServletRequest req) throws IOException { 
		// request로 받아온 file을 불러낼 클래스
		System.out.println("파일 업로드 처리");
		System.out.println("업로드 된 파일 이름 : " + upfiles.getOriginalFilename());
		// getName() 객체명, getOriginalFilename 객체 내 파일 명
		// post 방식으로 밖에 작동 되지 않는다.
		System.out.println("업로드된 파일 타입 : " + upfiles.getContentType());

		String originFilename = upfiles.getOriginalFilename();
		String originFileType = upfiles.getContentType();
		byte[] upfilesContent = upfiles.getBytes(); // 실제 파일의 내용

		String realPath = req.getSession().getServletContext().getRealPath("resources/upfiles");
		// request로부터 받아온 servlet 객체를 저장 할 경로(저장될 물리적 경로)
		// 현재실행중인session 얻어오고 -> Session에서 실행중인 Context를 얻어온 뒤 -> 실질적인 경로를 얻어온다.

		UploadFileInfo fileInfo =
				UploadFilesProc.uploadFile(originFilename, originFileType, upfilesContent, realPath);
		if(fileInfo !=null) {
			this.upfileList.add(fileInfo);
		}
		for(UploadFileInfo ufi : this.upfileList) {
			System.out.println(ufi.toString());
		}
		
		return fileInfo;
	}

}
