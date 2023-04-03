package com.springbasic2.domain;

import com.google.protobuf.Timestamp;

public class MemberPointVO {
	
	//기본 생성자 필수....
	public MemberPointVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int no;
	private String who;
	private Timestamp when; // date 타입은 sql 과 연동 시킬 때 TimeStamp 가져오면 쓰기 편하다
	private String why;
	private int howmuch;

	public int getNo() {
		return no;
	}

	public String getWho() {
		return who;
	}

	public Timestamp getWhen() {
		return when;
	}

	public String getWhy() {
		return why;
	}

	public int getHowmuch() {
		return howmuch;
	}

	public MemberPointVO(int no, String who, Timestamp when, String why, int howmuch) {
		this.no = no;
		this.who = who;
		this.when = when;
		this.why = why;
		this.howmuch = howmuch;
	}

	@Override
	public String toString() {
		return "MemberPoinVo [no=" + no + ", who=" + who + ", when=" + when + ", why=" + why + ", howmuch=" + howmuch
				+ "]";
	}

}
