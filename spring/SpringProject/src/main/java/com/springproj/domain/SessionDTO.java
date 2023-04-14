package com.springproj.domain;

import java.sql.Timestamp;

public class SessionDTO {
	private String sesId;
	private Timestamp seslimit;
	private String userId;
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public SessionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "SessionDTO [sesId=" + sesId + ", seslimit=" + seslimit + ", userId=" + userId + "]";
	}

	public SessionDTO(String sesId, Timestamp seslimit, String userId) {
		this.sesId = sesId;
		this.seslimit = seslimit;
		this.userId = userId;
	}

	public String getSesId() {

		return sesId;
	}

	public void setSesId(String sesId) {
		this.sesId = sesId;
	}

	public Timestamp getSeslimit() {
		return seslimit;
	}

	public void setSeslimit(Timestamp seslimit) {
		this.seslimit = seslimit;
	}

	

}
