package com.code.solvers.model;

import java.io.Serializable;

public class DateVsPrice implements Serializable {

	private static final long serialVersionUID = 4648511858567123747L;
	private String date;
	private long value;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
}
