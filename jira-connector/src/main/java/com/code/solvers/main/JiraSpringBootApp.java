package com.code.solvers.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.code.solvers.rest.JiraEndpoint;
import com.code.solvers.starter.JiraConnector;

@SpringBootApplication
@ComponentScan(basePackageClasses={JiraEndpoint.class, JiraConnector.class})
public class JiraSpringBootApp {
	
	public static void main(String[] args) {
		SpringApplication.run(JiraSpringBootApp.class, args);
	}
}
