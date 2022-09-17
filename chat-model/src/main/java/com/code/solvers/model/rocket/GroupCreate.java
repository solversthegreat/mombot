package com.code.solvers.model.rocket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GroupCreate implements Serializable{

	private static final long serialVersionUID = 1757787608018834039L;

	private String name;
	private List<String>members;
	private String readOnly;
	
	public GroupCreate() {
		this.members = new ArrayList<>();
		this.readOnly = "false";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getMembers() {
		return members;
	}
	public void setMembers(List<String> members) {
		this.members = members;
	}
	public String isReadOnly() {
		return readOnly;
	}
	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}
	
	@Override
	public String toString() {
		return "GroupCreate [name=" + name + ", members=" + members + ", readOnly=" + readOnly + "]";
	}
	
}
