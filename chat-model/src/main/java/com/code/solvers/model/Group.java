package com.code.solvers.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable {

	private static final long serialVersionUID = -5194519973749033342L;
	public ArrayList<User> users;
    public int count;
    public int offset;
    public int total;
    public boolean success;
	public ArrayList<User> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<User> users) {
		this.users = users;
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
