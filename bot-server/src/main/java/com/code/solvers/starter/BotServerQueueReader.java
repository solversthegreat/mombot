package com.code.solvers.starter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

//import com.code.solvers.dialogflow.model.response.DialogFlowQueryResponse;
//import com.code.solvers.dialogflow.model.response.Message;
import com.code.solvers.model.AllUrls;
import com.code.solvers.model.BotServerIncomingMessage;
import com.code.solvers.model.BotServerOutgoingMessage;
import com.code.solvers.queue.BotServerMessagePoster;
import com.code.solvers.queue.BotServerQueue;

public class BotServerQueueReader implements Runnable {

	Logger logger = Logger.getLogger(BotServerQueueReader.class);
	
	BotServerQueue queue;
	
	private BotServerMessagePoster messagePoster;
	private RestTemplate restTemplate;
	private SessionStore sessionStore;
	private List<String> errors;
	
	public BotServerQueueReader(BotServerQueue queue, BotServerMessagePoster messagePoster, SessionStore session, RestTemplate template) {
		this.queue = queue;
		this.messagePoster = messagePoster;
		this.restTemplate = template;
		this.sessionStore = session;
		
		errors = new ArrayList<String>();
		errors.add("There is an error connecting to DialogFlow. Please try again :-).");
		errors.add("Dialogflow is not reachable. Probably because of slow internet connection. Please try again :-)");
		errors.add("There is an error connecting to DialogFlow. Please try again.");
		errors.add("Please try again as DialogFlow is not reachable. :-)");
		errors.add("Error connecting to DialogFlow Servers. Please try one more time :-)");
	}

	public String getRandomError() {
		Random r = new Random();
		int b = r.nextInt(errors.size());
		return errors.get(b);
	}
	
	@Override
	public void run() {
		logger.info("**********Bot Server Started********");
		
		while(true) {
			try {
				BotServerIncomingMessage message = queue.pop();
				
				String userId = message.getRocketIncomingMessage().getUser_id();
				UserSession userSession = sessionStore.SESSION.get(userId);
				
				if(userSession!= null) {
					message.setLastResponse(userSession.getLastResponse());
					message.setSessionId(userSession.getSessionId());
				} else {
					userSession = new UserSession();
					userSession.setSessionId(UUID.randomUUID().toString());
					message.setSessionId(userSession.getSessionId());
				}
						
				BotServerOutgoingMessage omessage = new BotServerOutgoingMessage();
				omessage.setIncomingMessage(message);
				
				HttpEntity<DialogFlowQueryResponse> response = queryDialogflow(message);
				
				if(response != null && response.getBody() != null) {
					omessage.setResponseText(getTextOutofResponse(response.getBody()));
					userSession.setLastResponse(response.getBody());
					userSession.setLastResponseTime(new Date());
					sessionStore.SESSION.put(userId, userSession);
					
				} else {
					omessage.setResponseText(getRandomError());
				}
				
				logger.info("Sending message to Rocket :"+omessage.getResponseText());
				messagePoster.postMessageToRocket(omessage);
				
			} catch (Exception e) {
				logger.error("Error in thread :", e);
			}
		}
	}
	
	

	private String getTextOutofResponse(DialogFlowQueryResponse response) {
		StringBuilder sb = new StringBuilder();
		for(Message m : response.getResult().getFulfillment().getMessages()) {
			sb.append(m.getSpeech()).append("\n");
		}
		
		return sb.toString();
	}

	private HttpEntity<DialogFlowQueryResponse> queryDialogflow(BotServerIncomingMessage message) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<BotServerIncomingMessage> requestObj = new HttpEntity<BotServerIncomingMessage>(message, headers);
		
		ResponseEntity<DialogFlowQueryResponse> resp = null;
		try {
			resp = restTemplate.postForEntity(AllUrls.BOT_SERVER_TO_DIALOG_FLOW, requestObj, DialogFlowQueryResponse.class);
			return resp;
		} catch(Exception e) {
			logger.error("Error connecting to dialogflow :", e);
			return null;
		}
	}
}
