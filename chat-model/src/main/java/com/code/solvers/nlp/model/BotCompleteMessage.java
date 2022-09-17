package com.code.solvers.nlp.model;

import java.io.Serializable;

import com.code.solvers.model.rocket.RocketIncomingMessage;

public class BotCompleteMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private RocketIncomingMessage incomingMessage;
	private String rocketBotUserId;
	private String rocketBotAuthToken;

	public RocketIncomingMessage getIncomingMessage() {
		return incomingMessage;
	}
	public void setIncomingMessage(RocketIncomingMessage incomingMessage) {
		this.incomingMessage = incomingMessage;
	}
	public String getRocketBotUserId() {
		return rocketBotUserId;
	}
	public void setRocketBotUserId(String rocketBotUserId) {
		this.rocketBotUserId = rocketBotUserId;
	}
	public String getRocketBotAuthToken() {
		return rocketBotAuthToken;
	}
	public void setRocketBotAuthToken(String rocketBotAuthToken) {
		this.rocketBotAuthToken = rocketBotAuthToken;
	}
}
