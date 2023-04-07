package com.springproj.domain;

//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
////Getter,Setter 자동 생성
//@Getter
//@Setter
//@ToString // toString() 자동 생성
////boardImgNo 변수, boardNo 변수의 값이 같으면 같은 객체로 인식되도록 equals(),hashcode()를 오버라이딩 자동생성
//@EqualsAndHashCode(of = {"boardImgNo", "boardNo"}) 
//@Data //@Tostring,@EqualsAndHashCode,@Getter,@Setter,@RequiredArgsConstructor 를 모두 자동 생성 시켜줌
public class BoardImg {
	private int boardImgNo;
	private int boardNo;
	private String fileType;
	private String fileExt;
	private String fileName;
	private String thumbFileName;
	private String base64File;

	public BoardImg() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardImg(int boardImgNo, int boardNo, String fileType, String fileExt, String fileName, String thumbFileName,
			String base64File) {
		this.boardImgNo = boardImgNo;
		this.boardNo = boardNo;
		this.fileType = fileType;
		this.fileExt = fileExt;
		this.fileName = fileName;
		this.thumbFileName = thumbFileName;
		this.base64File = base64File;
	}

	@Override
	public String toString() {
		return "BoardImg [boardImgNo=" + boardImgNo + ", boardNo=" + boardNo + ", fileType=" + fileType + ", fileExt="
				+ fileExt + ", fileName=" + fileName + ", thumbFileName=" + thumbFileName + ", base64File=" + base64File
				+ "]";
	}

	public int getBoardImgNo() {
		return boardImgNo;
	}

	public void setBoardImgNo(int boardImgNo) {
		this.boardImgNo = boardImgNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getThumbFileName() {
		return thumbFileName;
	}

	public void setThumbFileName(String thumbFileName) {
		this.thumbFileName = thumbFileName;
	}

	public String getBase64File() {
		return base64File;
	}

	public void setBase64File(String base64File) {
		this.base64File = base64File;
	}

}
