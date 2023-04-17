package com.springproj.domain;

public class BoardLikeDTO {
	private int boardNo;
	private String who;
	private int likeNo;
	private boolean isLike;
	
	public BoardLikeDTO() {
		super();
	}
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}
	public boolean isLike() {
		return isLike;
	}
	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}
	public int getLikeNo() {
		return likeNo;
	}
	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}
	@Override
	public String toString() {
		return "BoardLikeDTO [boardNo=" + boardNo + ", who=" + who + ", isLike=" + isLike + ", likeNo=" + likeNo + "]";
	}

	public BoardLikeDTO(int boardNo, String who, int likeNo, boolean isLike) {
		this.boardNo = boardNo;
		this.who = who;
		this.likeNo = likeNo;
		this.isLike = isLike;
	}
	

	

	
}
