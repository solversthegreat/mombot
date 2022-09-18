package com.code.solvers.model;

import java.io.Serializable;

public class Email implements Serializable {
	private static final long serialVersionUID = 6017240586358265708L;
	public String address;
    public boolean verified;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
    
}
