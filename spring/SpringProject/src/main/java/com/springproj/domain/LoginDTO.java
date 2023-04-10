package com.springproj.domain;

public class LoginDTO {
	private String userId;
	private String userPwd;
	private boolean remember;

	public LoginDTO() {
		super();
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public LoginDTO(String userId, String userPwd, boolean remember) {
		this.userId = userId;
		this.userPwd = userPwd;
		this.remember = remember;
	}

	@Override
	public String toString() {
		return "LoginDTO [userId=" + userId + ", userPwd=" + userPwd + ", remember=" + remember + "]";
	}

}
