package com.code.solvers.jira.model;

import java.io.Serializable;

public class JiraWebhookInput implements Serializable {

	private static final long serialVersionUID = 1L;
	private String webhookEvent;
	private JiraIssue issue;
	
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
