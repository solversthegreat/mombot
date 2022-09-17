package com.code.solvers.starter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.code.solvers.queue.BotServerMessagePoster;
import com.code.solvers.queue.BotServerQueue;

@Component
public class BotServer {
	
	@Autowired
	public BotServer(BotServerQueue queue, BotServerMessagePoster messageposter, SessionStore sessionStore, RestTemplate restTemplate) {

		ExecutorService es = Executors.newFixedThreadPool(1);
		es.execute(new BotServerQueueReader(queue, messageposter, sessionStore, restTemplate));
	}
}
