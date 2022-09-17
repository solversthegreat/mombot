package com.code.solvers.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Data implements Serializable {

	private String authToken;
	private String userId;
	private Me me;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -5885184895673588216L;

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Me getMe() {
		return me;
	}

	public void setMe(Me me) {
		this.me = me;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "Data [authToken=" + authToken + ", userId=" + userId + ", me="
				+ me + ", additionalProperties=" + additionalProperties + "]";
	}
}
