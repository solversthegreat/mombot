package com.code.solvers.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Channel implements Serializable {

	private static final long serialVersionUID = 8152008248205871497L;
	private ArrayList<Member> members;
	private int count;
	private int offset;
	private int total;
	private boolean success;
	
	public ArrayList<Member> getMembers() {
		return members;
	}
	public void setMembers(ArrayList<Member> members) {
		this.members = members;
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
