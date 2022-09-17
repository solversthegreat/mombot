package com.code.solvers.model.rocket;

import java.util.List;

import com.code.solvers.model.User;

public class Group {
	private String _id;
	private String name;
	private String t;
	private List<String>usernames;
	private String msgs;
	private boolean ro;
	private boolean sysMes;
	private String _updatedAt;
	private String ts;
	private User u;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
	public List<String> getUsernames() {
		return usernames;
	}
	public void setUsernames(List<String> usernames) {
		this.usernames = usernames;
	}
	public String getMsgs() {
		return msgs;
	}
	public void setMsgs(String msgs) {
		this.msgs = msgs;
	}
	public boolean isRo() {
		return ro;
	}
	public void setRo(boolean ro) {
		this.ro = ro;
	}
	public boolean isSysMes() {
		return sysMes;
	}
	public void setSysMes(boolean sysMes) {
		this.sysMes = sysMes;
	}
	public String get_updatedAt() {
		return _updatedAt;
	}
	public void set_updatedAt(String _updatedAt) {
		this._updatedAt = _updatedAt;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}
}
