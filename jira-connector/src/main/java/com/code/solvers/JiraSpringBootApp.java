package com.code.solvers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class JiraSpringBootApp {
	
	public static void main(String[] args) {
		SpringApplication.run(JiraSpringBootApp.class, args);
	}
}
