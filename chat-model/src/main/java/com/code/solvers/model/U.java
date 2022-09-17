package com.code.solvers.model;

import java.io.Serializable;

public class U implements Serializable {
	
	private static final long serialVersionUID = 3281301075846623153L;
	private String _id;
	private String username;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
