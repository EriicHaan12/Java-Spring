package com.springproj.etc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class ImgMimeType {
	private static Map<String, MediaType> imgMimeType;

// {
//	 // instance 멤버 변수를 초기화 블럭
// }

	static {
		// static 한 멤버 변수를 초기화 하는 블럭
		imgMimeType = new HashMap<String, MediaType>();
		// 이미지 확장자 등록하기
		imgMimeType.put("jpg", MediaType.IMAGE_JPEG);
		imgMimeType.put("jpeg", MediaType.IMAGE_JPEG);
		imgMimeType.put("jfif", MediaType.IMAGE_JPEG);
		imgMimeType.put("gif", MediaType.IMAGE_JPEG);
		imgMimeType.put("png", MediaType.IMAGE_JPEG);
	}

	public static MediaType getMediaType(String ext) {
		// Key로써 value 값 얻어 오기
		
		//검사하기 전 대문자로 넘어오면 소문자로 바꿔서 검사
		return imgMimeType.get(ext.toLowerCase());
	};
}
