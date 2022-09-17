package com.code.solvers.nlp.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.code.solvers.jira.model.JiraIssue;
import com.code.solvers.jira.model.Status;

public class Response implements Serializable {

	private String id;
	private String timestamp;
	private String lang;
	private Status status;
	private String sessionId;
	private OriginalRequest originalRequest;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -8865944071835456275L;

	// for jira
	private String webhookEvent;
	private JiraIssue issue;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public void setOriginalRequest(OriginalRequest originalRequest) {
		this.originalRequest = originalRequest;
	}
	
	public OriginalRequest getOriginalRequest() {
		return originalRequest;
	}

	public String getWebhookEvent() {
		return webhookEvent;
	}

	public void setWebhookEvent(String webhookEvent) {
		this.webhookEvent = webhookEvent;
	}

	public JiraIssue getIssue() {
		return issue;
	}

	public void setIssue(JiraIssue issue) {
		this.issue = issue;
	}
}
