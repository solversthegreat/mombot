package com.code.solvers.starter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.code.solvers.model.AllUrls;
import com.code.solvers.model.Data;
import com.code.solvers.model.RocketLoginRequestPojo;
import com.code.solvers.model.RocketLoginResponse;
import com.code.solvers.queue.RocketAdapterQueue;

@Component
public class RocketAdapter {

	RestTemplate restTemplate;

	private Data loginData;

	private Logger logger = Logger.getLogger(RocketAdapter.class);
	
	@Autowired
	public RocketAdapter(RocketAdapterQueue queue, RestTemplate restTemplate) {

		this.restTemplate = restTemplate;
		loginToRocket(0);
		logger.info("*****Rocket login successful*********");
		ExecutorService es = Executors.newFixedThreadPool(1);
		es.execute(new RocketAdapterQueueReader(queue, this, restTemplate));
	}

	private void loginToRocket(int attempt) {
		RocketLoginRequestPojo loginPojo = new RocketLoginRequestPojo();
		loginPojo.setUsername("hack2022");
		loginPojo.setPassword("hack@2022");

		RocketLoginResponse response = null;

		try {
			response = restTemplate.postForObject(AllUrls.ROCKET_LOGIN_ENDPOINT, loginPojo, RocketLoginResponse.class);

			if (response.getStatus().equals("success")) {
				setLoginData(response.getData());
				
				logger.info("Login successful. Response is:"+response);
			} else {
				logger.error("Failed to login to rocket using credentials: "
						+ loginPojo + ". Response received :" + response);
			}
		} catch (Exception e) {
			logger.error("Failed to login to rocket using credentials: "+ loginPojo.getUsername() + ". Response received :" + e.getMessage());
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			loginToRocket(attempt++);
		}
	}

	public Data getLoginData() {
		return loginData;
	}

	public void setLoginData(Data loginData) {
		this.loginData = loginData;
	}
}
