package com.code.solvers.config;

import java.time.Duration;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;

import com.code.solvers.model.AllUrls;

@Configuration
public class PublicAPIConfiguration {
	

	@LoadBalanced
    @Bean
    RestTemplate restTemplate() {
		return new RestTemplateBuilder()
				.setConnectTimeout(Duration.ofMillis(AllUrls.TIMEOUT_IN_MILLISECOND))
				.setReadTimeout(Duration.ofMillis(AllUrls.TIMEOUT_IN_MILLISECOND)).build();
    }
	

}
