package com.springproj.etc;

import java.io.File;
import java.util.Calendar;

public class UploadFilesProc {

	public static void uploadFile(String originFilename, String originFileType, byte[] upfilesContent,
			String realPath) {
		// 여기에서는 업로드된 파일이 업로드된 날짜의 폴더 밑에 저장되도록 하자.
		// 예)
		System.out.println("업로드된 파일 저장처리 할 곳");

		makeCalPath(realPath, originFilename);

	}

	// 업로드 날짜에 맞는 폴더가 있으면 맞게 날짜에 맞게끔 폴더를 찾아 저장되도록...
	// 저장은 안되더라도
	// 업로드 날짜를 확인하고 그 날짜가 없다면 날짜에 맞는 폴더 생성

	private static void makeCalPath(String realPath, String originFilename) {
	//Calendar 클래스
		//file.mkdir
	
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month= cal.get(Calendar.MONTH)+1;
		int date= cal.get(Calendar.DATE);
		System.out.println("업로드된 파일이 저장된 연도 : " + year);
		System.out.println("업로드된 파일이 저장된 월 : " + month);
		System.out.println("업로드된 파일이 저장된 일 : " + date);
		
		String path = realPath;
		path+= "\\"+year;
		path+="\\"+month;
		path+="\\"+date;
		path+="\\"+originFilename;
		System.out.println("파일이 실질적으로 저장될 경로 : " + path);
		//여기서부터 다시...
		File saveFile = new File(path); 
			if(!saveFile.exists()) {
				saveFile.mkdir();
			}
	}
}
