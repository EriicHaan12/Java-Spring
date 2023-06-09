package com.springproj.etc;

import com.springproj.domain.BoardImg;

public class UploadFileInfo {
	private String originFileName; // 경로 없는 unique한 파일 이름
	private String fileNameWithExt;// 경로가 포함 되어있음
	private String ext;
	private String mimeType;
	private String base64Str; // 이미지인 경우 base64로 인코딩하기 위해
	private String thumbImgName; // 썸네일 파일 이름(이미지인 경우), 경로가 포함 되어있음
	private boolean isImage; // 이미지 타입인지 유효성 검사용.

	public UploadFileInfo(String originFileName, String fileNameWithExt, String ext, String mimeType, String base64Str,
			String thumbImgName, boolean isImage) {
		this.originFileName = originFileName;
		this.fileNameWithExt = fileNameWithExt;
		this.ext = ext;
		this.mimeType = mimeType;
		this.base64Str = base64Str;
		this.thumbImgName = thumbImgName;
		this.isImage = isImage;
	}

	public UploadFileInfo(BoardImg bi) {
		this.base64Str = bi.getBase64File();
		this.originFileName=bi.getFileName();
		this.ext = bi.getFileExt();
		this.fileNameWithExt = bi.getFileName();
		this.mimeType = bi.getFileType();
		this.thumbImgName = bi.getThumbFileName();

		if (bi.getThumbFileName().equals("")) {
			this.isImage = false;
		} else {
			this.isImage = true;
		}
	}

	public UploadFileInfo(String originFileName, String ext) {
		this(originFileName, "", ext, "", "", "", false);
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	public String getFileNameWithExt() {
		return fileNameWithExt;
	}

	public void setFileNameWithExt(String fileNameWithExt) {
		this.fileNameWithExt = fileNameWithExt;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getBase64Str() {
		return base64Str;
	}

	public void setBase64Str(String base64Str) {
		this.base64Str = base64Str;
	}

	public String getThumbImgName() {
		return thumbImgName;
	}

	public void setThumbImgName(String thumbImgName) {
		this.thumbImgName = thumbImgName;
	}

	public boolean isImage() {
		return isImage;
	}

	public void setImage(boolean isImage) {
		this.isImage = isImage;
	}

	@Override
	public String toString() {
		return "UploadFileInfo [originFileName=" + originFileName + ", fileNameWithExt=" + fileNameWithExt + ", ext="
				+ ext + ", mimeType=" + mimeType + ", thumbImgName=" + thumbImgName + ", isImage=" + isImage + "]";
	}

}