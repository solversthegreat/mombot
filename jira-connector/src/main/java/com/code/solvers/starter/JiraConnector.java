package com.code.solvers.starter;

import java.util.Base64;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.code.solvers.jira.model.JiraRequestMessage;
import com.code.solvers.jira.model.JiraResponseMessage;
import com.code.solvers.model.AllUrls;

@Component
public class JiraConnector {

	RestTemplate restTemplate;

	private Logger logger = Logger.getLogger(JiraConnector.class);

	@Autowired
	public JiraConnector(RestTemplateBuilder rtb) {
		restTemplate = rtb.setConnectTimeout(AllUrls.TIMEOUT_IN_MILLISECOND).setReadTimeout(AllUrls.TIMEOUT_IN_MILLISECOND).build();
	}

	public HttpEntity<JiraResponseMessage> processJiraRequest(JiraRequestMessage message) {
		
		HttpEntity<JiraResponseMessage> response;
		
		try {
			HttpEntity<JiraResponseMessage> r = restTemplate.postForEntity(AllUrls.JIRA_CREATE_ISSUE_ENDPOINT, getJiraRequest(message), JiraResponseMessage.class);
			response = new HttpEntity<JiraResponseMessage>(r.getBody(), r.getHeaders());
			return response;
			
		} catch(Exception e) {
			logger.error("Error while creating issue in JIRA: ", e);
		}
		
		return new HttpEntity<JiraResponseMessage>(new JiraResponseMessage());
	}
	
	private HttpEntity<JiraRequestMessage> getJiraRequest(JiraRequestMessage message) {
		return new HttpEntity<JiraRequestMessage>(message, getHeaders());
	}
	
	private HttpHeaders getHeaders() {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + getAuthorization());
		headers.setContentType(MediaType.APPLICATION_JSON);

		return headers;
	}
	
	private String getAuthorization() {
		String auth = "";
		String authString = "grt.goblin@gmail.com:Indi@#123";//+ "yTMUWblaQN2KU60sddny519A";
		auth = Base64.getEncoder().encodeToString(authString.getBytes());
		
		return auth;
	}
}
