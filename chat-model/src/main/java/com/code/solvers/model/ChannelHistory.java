package com.code.solvers.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ChannelHistory implements Serializable {
	
	private static final long serialVersionUID = -2451749095386759923L;
	private ArrayList<Message> messages;
	private boolean success;
	
	public ArrayList<Message> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
