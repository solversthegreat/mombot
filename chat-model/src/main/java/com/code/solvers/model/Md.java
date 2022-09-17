package com.code.solvers.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Md implements Serializable {
	
	private static final long serialVersionUID = 9169807755673517733L;
	public String type;
    public ArrayList<Value> value;
    
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<Value> getValue() {
		return value;
	}
	public void setValue(ArrayList<Value> value) {
		this.value = value;
	}
    
}
