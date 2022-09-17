package com.code.solvers.model;

import java.io.Serializable;
import java.util.ArrayList;

public class GroupMessages implements Serializable {
	
	private static final long serialVersionUID = 6822198206551460457L;
	private ArrayList<Message> messages;
	private int count;
	private int offset;
	private int total;
	private boolean success;
	
	public ArrayList<Message> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}