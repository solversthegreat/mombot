package com.code.solvers.model;

import java.io.Serializable;

public class BotServerOutgoingMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private BotServerIncomingMessage incomingMessage;
	private String responseText;
	
	public BotServerIncomingMessage getIncomingMessage() {
		return incomingMessage;
	}
	public void setIncomingMessage(BotServerIncomingMessage incomingMessage) {
		this.incomingMessage = incomingMessage;
	}
	public String getResponseText() {
		return responseText;
	}
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}
	@Override
	public String toString() {
		return "BotServerOutgoingMessage [incomingMessage=" + incomingMessage
				+ ", responseText=" + responseText + "]";
	}

	
}
