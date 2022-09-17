package com.code.solvers.model.rocket;

import java.io.Serializable;
import java.util.Date;

public class RocketIncomingMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String token;
	private String bot;
	private String channel_id;
	private String channel_name;
	private String message_id;
	private Date timestamp;
	private String user_id;
	private String user_name;
	private String text;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getBot() {
		return bot;
	}
	public void setBot(String bot) {
		this.bot = bot;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getChannel_name() {
		return channel_name;
	}
	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "RocketIncomingMessage [token=" + token + ", bot=" + bot
				+ ", channel_id=" + channel_id + ", channel_name="
				+ channel_name + ", message_id=" + message_id + ", timestamp="
				+ timestamp + ", user_id=" + user_id + ", user_name="
				+ user_name + ", text=" + text + "]";
	}
	
	
}
