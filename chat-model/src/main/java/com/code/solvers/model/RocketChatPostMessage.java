package com.code.solvers.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RocketChatPostMessage implements Serializable {
	
private static final long serialVersionUID = -4212099281676882762L;
	
	private String roomId;
    private String channel;
    private String text;
    private String alias;
    private String avatar;
    private String color;
    private List<RocketChatPostAttachment> chatPostAttachments;
    
	public RocketChatPostMessage() { 
		this.chatPostAttachments = new ArrayList<>();
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<RocketChatPostAttachment> getChatPostAttachments() {
		return chatPostAttachments;
	}

	public void setChatPostAttachments(List<RocketChatPostAttachment> chatPostAttachments) {
		this.chatPostAttachments = chatPostAttachments;
	}
	
}
