package com.code.solvers.model;

import java.io.Serializable;

//import com.code.pilots.dialogflow.model.response.DialogFlowOriginalRequest;
//import com.code.pilots.dialogflow.model.response.DialogFlowQueryResponse;
import com.code.solvers.model.rocket.RocketIncomingMessage;
// as per dialogflow contract
public class BotServerIncomingMessage implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	//private DialogFlowOriginalRequest originalRequest;
	private RocketIncomingMessage rocketIncomingMessage;
	//private DialogFlowQueryResponse lastResponse;
	private String sessionId;
	
	public RocketIncomingMessage getRocketIncomingMessage() {
		return rocketIncomingMessage;
	}

	public void setRocketIncomingMessage(RocketIncomingMessage rocketIncomingMessage) {
		this.rocketIncomingMessage = rocketIncomingMessage;
	}

	@Override
	public String toString() {
		return "BotServerIncomingMessage [rocketIncomingMessage="
				+ rocketIncomingMessage + "]";
	}

	/*
	 * public DialogFlowQueryResponse getLastResponse() { return lastResponse; }
	 * 
	 * public void setLastResponse(DialogFlowQueryResponse lastResponse) {
	 * this.lastResponse = lastResponse; }
	 */

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/*
	 * public DialogFlowOriginalRequest getOriginalRequest() { return
	 * originalRequest; }
	 * 
	 * public void setOriginalRequest(DialogFlowOriginalRequest originalRequest) {
	 * this.originalRequest = originalRequest; }
	 */
	
}
