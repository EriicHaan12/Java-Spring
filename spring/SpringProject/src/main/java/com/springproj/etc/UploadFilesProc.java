package com.springproj.etc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;
import org.springframework.util.FileCopyUtils;

/**
 * @author goott6
 * @param originFileName : 업로드 된 파일 이름(확장자 포함)
 * @param originFileType : 업로드 된 파일의 mimeType
 * @param upfilesContent : 업로드 된 파일으 ㅣ실제 contents
 * @param realPath
 */

public class UploadFilesProc {

	/**
	 * 삭제될 파일이 이미지인지 아닌지 판단하여 이미지면 (원본+썸네일 삭제) 이미지가 아니라면 원본 파일 삭제
	 * @param ufi      : 삭제될 파일의 파일 정보
	 * @param realPath : /resources/upfiles의 물리적 경로
	 **/
	public static void deleteUpFile(UploadFileInfo ufi, String realPath) {
		// 원본 삭제
		File target = new File(realPath + ufi.getFileNameWithExt());
		target.delete();
		if (ufi.isImage()) {
			// 썸네일 삭제
			File targetThumb = new File(realPath + ufi.getThumbImgName());
			targetThumb.delete();
		}
	}

	public static UploadFileInfo uploadFile(String originFileName, String originFileType, byte[] upfilesContent,
			String realPath) {
		String completePath = makeCalPath(realPath); // 실제 저장 경로
		

		String customPath = completePath.substring(realPath.length());
		System.out.println("파일 이름에 날짜를 붙여줄 path : " + customPath);

		// FileNameAndExt 객체(unique한 새 파일이름, 확장자)
		UploadFileInfo uniqueFileName = makeNewUniqueFileName(originFileName);
		uniqueFileName.setMimeType(originFileType);

		System.out.println("궁극적 저장 경로 : " + completePath);
		System.out.println("궁극적 저장될 파일 이름 : " + uniqueFileName.toString());

		String savedFile = completePath + File.separator + uniqueFileName.getOriginFileName();

		System.out.println("저장될 파일 주소(주소+파일이름+확장자) : " + savedFile);
		uniqueFileName.setFileNameWithExt(customPath + File.separator + uniqueFileName.getOriginFileName());

		System.out.println("저장될 파일 이름 : " + savedFile);

		File saveTarget = new File(savedFile);

		try {
			// 실제 파일 저장
			FileCopyUtils.copy(upfilesContent, saveTarget);

			// 이미지 파일인지 아닌지 검사
			if (ImgMimeType.getMediaType(uniqueFileName.getExt()) != null) { // 이미지 파일 이면
				System.out.println("이미지 파일 업로드 성공!");
				uniqueFileName.setImage(true);
				uniqueFileName.setBase64Str(makeFileToBase64String(savedFile));

				makeThumbnailImage(savedFile, uniqueFileName, completePath, customPath);
			}
		} catch (IOException e) {
			// 저장 실패 할 경우
			uniqueFileName = null;
		}
		System.out.println(uniqueFileName.toString());

		return uniqueFileName;
	}

	private static void makeThumbnailImage(String savedFile, UploadFileInfo uniqueFileName, String completePath,
			String customPath) throws IOException {
		boolean result = false;

		// 저장된 파일에 이미지 읽어오기
		BufferedImage originImg = ImageIO.read(new File(savedFile));// 원본
		BufferedImage thumbImg = Scalr.resize(originImg, Mode.FIT_TO_HEIGHT, 50, null);

		// 썸네일용 이미지 파일 이름 지정
		String thumbImgName = "thumb_" + uniqueFileName.getOriginFileName();

		// String thumbImgName = "thumb_" + uniqueFileName.getFileNameWithExt();
		// 이름에 날짜까지 같이 저장(정확한 저장경로 데이터를 가져오기 위해서)

		// 썸네일 이미지 파일 저장
		File saveTarget = new File(completePath + File.separator + thumbImgName);

		if (ImageIO.write(thumbImg, uniqueFileName.getExt(), saveTarget)) {
			uniqueFileName.setThumbImgName(customPath + File.separator + thumbImgName);
		}

		// 이미지 사이즈 크기를 int로 가져오는 메서드
		System.out.println("이미지 사이즈 : " + originImg.getHeight());
		// uniqueFileName.setFileNameWithExt(customPath+File.separator+uniqueFileName.getFileNameWithExt());

		System.out.println("썸네일용 이미지 이름 : " + thumbImgName);
		// .jfif 파일은 이미지파일이지만
		// 인식이 안되기 때문에 썸네일용 이미지로 저장이 되지 않는다.(그냥 이미지는 저장됨)
	}

	// 다른곳에서 호출될수 있도록 static으로 바꿔주자
	private static String makeFileToBase64String(String savedFile) throws IOException {
		// base64 문자열 : 2진 데이터 파일을 읽어서 A-Za-z0-9+/ 문자의 조합으로 바꾼것.
		// 즉 데이터 파일을 문자열만 표현해놓은것.

		String result = null;
		File upfile = new File(savedFile);
		// 업로드할 String 타입의 savedFile을 File 클래스의 객체로 만들어준다.

		// byte 타입의 배열 file 을 생성한 뒤
		// 파일을 읽어서 btye 단위의 배열로 생성하는 함수 쓰기
		byte[] file = FileUtils.readFileToByteArray(upfile); // 업로드 된 파일 읽기
		result = Base64.getEncoder().encodeToString(file);// 읽은 파일을 base64 방식으로 인코딩
		System.out.println("base64로 인코딩된 문자열 : " + result);

		return result;
	}

	/**
	 * @author goott6
	 * @param originFileName : 업로드 된 파일 이름(확장자 포함)
	 * @return : FileNameAndExt 객체(unique한 새 파일이름, 확장자)
	 */
	private static UploadFileInfo makeNewUniqueFileName(String originFileName) {
		String newFileName = "";
		String ext = originFileName.substring(originFileName.lastIndexOf(".") + 1);// 확장자명 얻어오기
		String uuid = UUID.randomUUID().toString(); // 16진수 128bit 랜덤한 유니크값 생성

		// newFileName = uuid + "_" + originFileName + "." + ext;
		newFileName = uuid + "_" + originFileName;
		System.out.println("저장될 새로운 파일 이름 : " + newFileName);// unique 값으로 저장될 새로운 파일명

		return new UploadFileInfo(newFileName, ext);
	}

	/**
	 * 여기에서는 업로드된 파일이 업로드된 날짜의 폴더 밑에 저장되도록 하자 예) 현재 날짜가 2023-04-05 라면,
	 * resources/upFiles/2023/04/05 하위에 저장 되도록 하자
	 * 
	 * @param realPath : 실제 저장될 물리적 경로
	 */
	private static String makeCalPath(String realPath) {
		System.out.println("물리적 경로 : " + realPath);
		Calendar cal = Calendar.getInstance();
		// Calendar를 문자열로 변환하기 위해 "" 사용
		// new DecimalFormat("00").format() : 10진수 2자리로 변환. 자릿수가 없으면 0으로 채워줌.
		String year = File.separator + cal.get(Calendar.YEAR) + ""; // \2023
		String month = year + File.separator + new DecimalFormat("00").format((cal.get(Calendar.MONTH) + 1)); // \2023\04
		String date = month + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE)); // \2023\04\05
		System.out.println("저장될 파일 연도 : " + year);
		System.out.println("저장될 파일 월 : " + month);
		System.out.println("저장될 파일 일 : " + date);

		makeDir(realPath, year, month, date);
		return realPath + date;
	}

	/**
	 * realPath 경로에 year, month, date 폴더가 있는지 확인하고, 없으면 directory를 생성
	 * 
	 * @param realPath
	 * @param ...paths : 가변인자 문법 (타입이 같을 때만 사용 가능) makeDir(realPath, year, month,
	 *                 date)에서 realPath를 제외한 매개변수가 String타입 변수 3개이다. year, month,
	 *                 date가 paths 변수에서 배열형식으로 모두 받게 된다.
	 */
	private static void makeDir(String realPath, String... paths) {

		if (!new File(realPath + paths[paths.length - 1]).exists()) {
			// realPath 경로 밑에 \년\월\일 폴더가 모두 존재 => 아래의 수행을 하지 않아도 됨
			for (String path : paths) {
				File tmp = new File(realPath + path);
				if (!tmp.exists()) { // 경로가 존재하지 않는다면
					tmp.mkdir();
				}
			}
		}
	}

}
