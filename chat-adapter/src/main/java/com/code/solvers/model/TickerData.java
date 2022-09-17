package com.code.solvers.model;

import java.io.Serializable;

public class TickerData implements Serializable {

	private static final long serialVersionUID = -3958191725576395104L;
	
	private DateVsPrice[] data;
	private String identifier;
	private String item;
	private int result_count;
	private int page_size;
	private int current_page;
	private int total_pages;
	private int api_call_credits;
	public DateVsPrice[] getData() {
		return data;
	}
	public void setData(DateVsPrice[] data) {
		this.data = data;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getResult_count() {
		return result_count;
	}
	public void setResult_count(int result_count) {
		this.result_count = result_count;
	}
	public int getPage_size() {
		return page_size;
	}
	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}
	public int getCurrent_page() {
		return current_page;
	}
	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}
	public int getTotal_pages() {
		return total_pages;
	}
	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}
	public int getApi_call_credits() {
		return api_call_credits;
	}
	public void setApi_call_credits(int api_call_credits) {
		this.api_call_credits = api_call_credits;
	}
}

