package com.code.solvers.builder;

import java.util.List;

import org.springframework.stereotype.Component;

import com.code.solvers.model.Message;


@Component
public class NLPInputRequestBuilder {
	
	public String buildSummaryRequest(List<Message> messages) {
		StringBuilder sb = new StringBuilder();
		
		for (Message message : messages) {
			sb.append(message.getU().getUsername()).append(": ").append(message.getMsg()).append(" . ").append(System.lineSeparator());
		}
		
		return sb.toString();
	}
}
