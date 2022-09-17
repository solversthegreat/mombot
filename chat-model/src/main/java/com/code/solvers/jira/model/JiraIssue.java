package com.code.solvers.jira.model;

public class JiraIssue {
	
	private String key;
	private JiraFieldsForWebhook fields;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public JiraFieldsForWebhook getFields() {
		return fields;
	}

	public void setFields(JiraFieldsForWebhook fields) {
		this.fields = fields;
	}
}
