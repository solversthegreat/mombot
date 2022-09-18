package com.code.solvers.rest;

import com.code.solvers.model.JiraRequestMessage;
import com.code.solvers.model.JiraResponseMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.solvers.starter.JiraConnector;

@RestController
public class JiraEndpoint {

	Logger logger = Logger.getLogger(JiraEndpoint.class);
	
	@Autowired
	private JiraConnector adapter;
	
	@RequestMapping("/jira/connector/incident")
	public HttpEntity<JiraResponseMessage> push(@RequestBody JiraRequestMessage message) {
		logger.info("Jira Create request received.");
		
		return adapter.processJiraRequest(message);
	}
	
}
