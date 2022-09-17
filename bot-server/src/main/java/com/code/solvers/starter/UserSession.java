package com.code.solvers.starter;

import java.io.Serializable;
import java.util.Date;

//import com.code.pilots.dialogflow.model.response.DialogFlowQueryResponse;

public class UserSession implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//private DialogFlowQueryResponse lastResponse;
	private Date lastResponseTime;
	private String sessionId;
	
	/*
	 * public DialogFlowQueryResponse getLastResponse() { return lastResponse; }
	 * public void setLastResponse(DialogFlowQueryResponse lastResponse) {
	 * this.lastResponse = lastResponse; }
	 */
	public Date getLastResponseTime() {
		return lastResponseTime;
	}
	public void setLastResponseTime(Date lastResponseTime) {
		this.lastResponseTime = lastResponseTime;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
