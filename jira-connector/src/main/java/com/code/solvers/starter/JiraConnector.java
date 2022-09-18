package com.code.solvers.starter;

import java.time.Duration;
import java.util.Base64;

import com.code.solvers.model.JiraRequestMessage;
import com.code.solvers.model.JiraResponseMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class JiraConnector {

	RestTemplate restTemplate;

	@Value("${http.timeout}")
	private long timeout;

	@Value("${jira.create.endpoint}")
	private String jiraCreateEndpoint;

	private Logger logger = Logger.getLogger(JiraConnector.class);

	@Autowired
	public JiraConnector(RestTemplateBuilder rtb) {
		restTemplate = rtb.setConnectTimeout(Duration.ofMillis(timeout))
				.setReadTimeout(Duration.ofMillis(timeout)).build();
	}

	public HttpEntity<JiraResponseMessage> processJiraRequest(JiraRequestMessage message) {
		
		HttpEntity<JiraResponseMessage> response;
		
		try {
			HttpEntity<JiraResponseMessage> r = restTemplate.postForEntity(jiraCreateEndpoint, getJiraRequest(message), JiraResponseMessage.class);
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
		//headers.add("Authorization", "Bearer " + getToken());
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String authString = "solversthegreat@gmail.com:" + getToken();
		String auth = Base64.getEncoder().encodeToString(authString.getBytes());
		
		headers.add("Authorization", "Basic " + auth);
		headers.setContentType(MediaType.APPLICATION_JSON);

		return headers;
	}
	
	private String getToken() {
		return "Tov2tU7rHXMENF1XVnkY39F4";
	}
}
