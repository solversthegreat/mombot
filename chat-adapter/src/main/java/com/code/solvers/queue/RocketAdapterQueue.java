package com.code.solvers.queue;

import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

import com.code.solvers.model.rocket.RocketIncomingMessage;

@Component
public class RocketAdapterQueue {

	private LinkedBlockingQueue<RocketIncomingMessage> messageQueue = new LinkedBlockingQueue<RocketIncomingMessage>();
	
	public void push(RocketIncomingMessage msg) throws InterruptedException {
		messageQueue.put(msg);
	}
	
	public RocketIncomingMessage pop() throws InterruptedException {
		return messageQueue.take();
	}
}
