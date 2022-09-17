package com.code.solvers.queue;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.solvers.model.BotServerIncomingMessage;

@RestController
public class BotServerQueueEndpoint {

	Logger logger = Logger.getLogger(BotServerQueueEndpoint.class);
	
	@Autowired
	private BotServerQueue botServerQueue;
	
	@RequestMapping("/bot/server/incoming/push")
	public Response push(@RequestBody BotServerIncomingMessage message) {
		
		logger.info("Bot Server incoming message received .");
		
		try {
			botServerQueue.push(message);
			
			logger.info("Bot Server incoming message queued successfully .");
			
		} catch (InterruptedException e) {
			logger.error("Error processing bot server incoming message", e);
		}
		
		return Response.ok().build();
	}
}
