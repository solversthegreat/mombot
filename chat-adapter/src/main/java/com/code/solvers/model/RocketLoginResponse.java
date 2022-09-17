package com.code.solvers.model;

import java.io.Serializable;

public class RocketLoginResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String status;
	private Data data;
	
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "RocketLoginResponse [status=" + status + ", data=" + data + "]";
	}
}
