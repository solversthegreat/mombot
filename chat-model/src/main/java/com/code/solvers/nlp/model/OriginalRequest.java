package com.code.solvers.nlp.model;

import java.io.Serializable;

public class OriginalRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String source;
	private BotCompleteMessage data;
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public BotCompleteMessage getData() {
		return data;
	}
	public void setData(BotCompleteMessage data) {
		this.data = data;
	}
}
