package com.code.solvers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI springShopApi() {
		return new OpenAPI()
				.info(new Info().title("chat-adapter-service")
						.description("Documentation for chat-adapter-service"));
				
	}
}
