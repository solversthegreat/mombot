package com.code.solvers.model;

import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable {
	private static final long serialVersionUID = 3840727022189788098L;
	private String _id;
	private String status;
	private Date _updatedAt;
	private String name;
	private String username;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date get_updatedAt() {
		return _updatedAt;
	}
	public void set_updatedAt(Date _updatedAt) {
		this._updatedAt = _updatedAt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
