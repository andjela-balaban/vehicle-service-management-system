package org.unibl.etf.dto;

import java.io.Serializable;

public class ChatMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	private int senderId;
	private String receiverId;
	private String content;
	private MessageType type;
	
	public ChatMessage() {
		super();
	}

	public ChatMessage(int senderId, String receiverId, String content, MessageType type) {
		super();
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.content = content;
		this.type = type;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}
	
	public boolean isGroupMessage() {
	    return type == MessageType.MULTICAST;
	}

	public int getReceiverIdAsInt() {
	    return Integer.parseInt(receiverId);
	}
	
	@Override
	public String toString() {
		return senderId + "#" + receiverId + "#" + content;
	}

}
