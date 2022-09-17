package com.code.solvers.jira.model;

import java.io.Serializable;

public class JiraRequestMessage implements Serializable {

	private static final long serialVersionUID = -7710618088967952665L;
	
	private Fields fields;
	
	public Fields getFields() {
		return fields;
	}
	public void setFields(Fields fields) {
		this.fields = fields;
	}
}

