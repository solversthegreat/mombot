package com.code.solvers.queue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.code.solvers.model.AllUrls;
import com.code.solvers.model.BotServerOutgoingMessage;

@Component
public class BotServerMessagePoster {

	@Autowired
	RestTemplate restTemplate;
	
	Logger logger = Logger.getLogger(BotServerMessagePoster.class);

	public void postMessageToRocket(BotServerOutgoingMessage msg) {
		try {
			
			HttpHeaders h = new HttpHeaders();
			h.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<BotServerOutgoingMessage> httpMsg = new HttpEntity<BotServerOutgoingMessage>(msg, h);
			Object postForObject = restTemplate.postForObject(AllUrls.BOT_SERVER_TO_ROCKET_PUSH, httpMsg, Object.class);
			
			logger.info("Message posted to rocket");
			
		} catch (Exception e) {
			logger.error("Error posting to Rocket :",e);
		}
	}
}
