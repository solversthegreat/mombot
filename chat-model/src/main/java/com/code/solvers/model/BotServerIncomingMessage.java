package com.code.solvers.model;

import java.io.Serializable;

import com.code.solvers.nlp.model.OriginalRequest;
import com.code.solvers.nlp.model.Response;
import com.code.solvers.model.rocket.RocketIncomingMessage;
// as per dialogflow contract
public class BotServerIncomingMessage implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	private OriginalRequest originalRequest;
	private RocketIncomingMessage rocketIncomingMessage;
	private Response lastResponse;
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

	
	public Response getLastResponse() { return lastResponse; }
	  
	public void setLastResponse(Response lastResponse) {
		this.lastResponse = lastResponse; 
	}
	 

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	
	public OriginalRequest getOriginalRequest() { 
	  return originalRequest; 
	}
  
	public void setOriginalRequest(OriginalRequest originalRequest) {
	  this.originalRequest = originalRequest; 
	}
	 
}
