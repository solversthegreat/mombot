package com.code.solvers.model;

import java.io.Serializable;

public class JiraResponseMessage implements Serializable {

	private static final long serialVersionUID = 1076885899968529351L;
	
	private String id;
	private String key;
	private String self;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
}
