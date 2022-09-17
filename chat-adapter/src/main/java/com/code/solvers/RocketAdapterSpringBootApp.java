package com.code.solvers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class RocketAdapterSpringBootApp {

	public static void main(String[] args) {
		SpringApplication.run(RocketAdapterSpringBootApp.class, args);
	}
}
