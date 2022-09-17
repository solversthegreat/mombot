package com.code.solvers.model;

import java.io.Serializable;

public class Value implements Serializable {

	private static final long serialVersionUID = -8921402920765632039L;
	private String type;
	private String value;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
