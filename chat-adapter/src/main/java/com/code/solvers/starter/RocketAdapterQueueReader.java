package com.code.solvers.starter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.code.solvers.nlp.model.BotCompleteMessage;
import com.code.solvers.nlp.model.OriginalRequest;
import com.code.solvers.model.AllUrls;
import com.code.solvers.model.BotServerIncomingMessage;
import com.code.solvers.model.rocket.RocketIncomingMessage;
import com.code.solvers.queue.RocketAdapterQueue;

public class RocketAdapterQueueReader implements Runnable {

	Logger logger = Logger.getLogger(RocketAdapterQueueReader.class);
	
	RocketAdapterQueue queue;
	RocketAdapter adapter;
	RestTemplate template;
	
	public RocketAdapterQueueReader(RocketAdapterQueue queue, RocketAdapter adapter, RestTemplate template) {
		this.queue = queue;
		this.adapter = adapter;
		this.template = template;
	}
	
	@Override
	public void run() {
		
		logger.info("***********Rocket Adapter Queue reader started");
		
		while(true) {
			try {
				RocketIncomingMessage message = queue.pop();
				BotServerIncomingMessage botServerMessage = new BotServerIncomingMessage();
				botServerMessage.setRocketIncomingMessage(message);
				
				OriginalRequest or = new OriginalRequest();
				or.setSource("rocket");
				BotCompleteMessage bfm = new BotCompleteMessage();
				bfm.setIncomingMessage(message);
				bfm.setRocketBotAuthToken(message.getToken());
				bfm.setRocketBotUserId(message.getUser_id());
				
				or.setData(bfm);
				
				botServerMessage.setOriginalRequest(or);
				
				logger.info("Message arrived in rocket adapter. Sending to botserver");
				
				try {
					template.postForObject(AllUrls.ROCKET_TO_BOT_SERVER_INCOMING, botServerMessage, Object.class);
					logger.info("Message posted to bot server successfully");
				} catch(Exception e) {
					logger.error("Error while posting message to botserver queue from rocketadapter",e);
				}
				
			} catch (InterruptedException e) {
				logger.error(e);
				break;
			}
		}
		
		logger.info("Rocket Adapter Queue reader stopped");
	}

}
