package com.springproj.domain;

import java.sql.Timestamp;

public class MessageVo {
	private int msgId;
	private String sender;
	private String receiver;
	private String messageText;
	private Timestamp sendTime;
	private String isRead;

	public MessageVo() {
		super();

	}

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public Timestamp getSendTime() {
		return sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	@Override
	public String toString() {
		return "MessageVo [msgId=" + msgId + ", sender=" + sender + ", receiver=" + receiver + ", messageText="
				+ messageText + ", sendTime=" + sendTime + ", isRead=" + isRead + "]";
	}

	public MessageVo(int msgId, String sender, String receiver, String messageText, Timestamp sendTime, String isRead) {
		this.msgId = msgId;
		this.sender = sender;
		this.receiver = receiver;
		this.messageText = messageText;
		this.sendTime = sendTime;
		this.isRead = isRead;
	}

}
