package com.code.solvers.queue;

import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

import com.code.solvers.model.BotServerIncomingMessage;

@Component
public class BotServerQueue {

	private LinkedBlockingQueue<BotServerIncomingMessage> messageQueue = new LinkedBlockingQueue<BotServerIncomingMessage>();
	
	public void push(BotServerIncomingMessage msg) throws InterruptedException {
		messageQueue.put(msg);
	}
	
	public BotServerIncomingMessage pop() throws InterruptedException {
		return messageQueue.take();
	}
}
