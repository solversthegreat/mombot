package com.code.solvers.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.code.solvers.model.AllUrls;
import com.code.solvers.queue.BotServerQueue;
import com.code.solvers.starter.BotServer;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackageClasses={BotServerQueue.class, BotServer.class})
public class BotServerSpringBootApp {
	
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder rtb) {
		return rtb.setConnectTimeout(AllUrls.TIMEOUT_IN_MILLISECOND).setReadTimeout(AllUrls.TIMEOUT_IN_MILLISECOND).build();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BotServerSpringBootApp.class, args);
	}
}
