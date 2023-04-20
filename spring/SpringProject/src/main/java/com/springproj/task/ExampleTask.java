package com.springproj.task;

import java.util.List;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springproj.domain.BoardImg;
import com.springproj.service.BoardService;

@Component
public class ExampleTask {

	@Inject
	private BoardService service;

	@Scheduled(cron = "* 2 * * * *")
	public void sampleTask() {
		//System.out.println("----------------- 작업 스케쥴링 시작 -----------------");

		try {
			List<BoardImg> lst = service.getAllAppendFiles();
			for(BoardImg bi : lst) {
				//1)DB에 저장되어 있는 파일들 :  bi.getFileName(); 해온 뒤 물리경로 +/resources/upfiles/bi.getFileName() 의 File 객체를 생성 
				
				//2)실제 하드디스크에 저장된 파일들 : 물리경로 +/resources/upfiles/ 하위의 모든 파일리스트를 가져온다  
				
				//3) 1번에서 만든 File 객체가 2번 리스트에 있다면, 그 파일은 제외 시키기
				// 2번에는 있고 1번에는 존재 하지 않는 파일을 삭제 (즉, 게시글 등록시 파일을 업로드 시켰지만, 실제 게시글은 등록하지 않았을 때) 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
