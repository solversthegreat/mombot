package com.code.solvers.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Md implements Serializable {
	
	private static final long serialVersionUID = 9169807755673517733L;
	public String type;
    public Object value;
    
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
    
}
