package com.code.solvers.model;

import java.io.Serializable;

public class RocketChatReact implements Serializable {
	private static final long serialVersionUID = -3243789091883862269L;

	private String emoji; // Required
	private String messageId; // Required
	private boolean shouldReact;
	
	public RocketChatReact() {
		this.shouldReact = false;
	}
	
	public String getEmoji() {
		return emoji;
	}
	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public boolean isShouldReact() {
		return shouldReact;
	}
	public void setShouldReact(boolean shouldReact) {
		this.shouldReact = shouldReact;
	}

	@Override
	public String toString() {
		return "RocketChatReact [emoji=" + emoji + ", messageId=" + messageId + ", shouldReact=" + shouldReact + "]";
	}
}
