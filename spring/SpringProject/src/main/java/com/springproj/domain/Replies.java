package com.springproj.domain;


import java.sql.Timestamp;

public class Replies {
	private int replyNo;
	private int boardNo;
	private String replier;
	private String replytext;
	private Timestamp postdate;
	private int ref;

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public Replies() {
		super();
		
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getReplier() {
		return replier;
	}

	public void setReplier(String replier) {
		this.replier = replier;
	}

	public String getReplytext() {
		return replytext;
	}

	public void setReplytext(String replytext) {
		this.replytext = replytext;
	}

	public Timestamp getPostdate() {
		return postdate;
	}

	public void setPostdate(Timestamp postdate) {
		this.postdate = postdate;
	}

	@Override
	public String toString() {
		return "Replies [replyNo=" + replyNo + ", boardNo=" + boardNo + ", replier=" + replier + ", replytext="
				+ replytext + ", postdate=" + postdate + "]";
	}

	public Replies(int replyNo, int boardNo, String replier, String replytext, Timestamp postdate) {
		this.replyNo = replyNo;
		this.boardNo = boardNo;
		this.replier = replier;
		this.replytext = replytext;
		this.postdate = postdate;
	}

}
