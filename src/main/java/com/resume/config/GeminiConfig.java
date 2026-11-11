package com.resume.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GeminiConfig {

	@Value("${gemini.api.key}")
	private String apiKey;
	
	@Bean
	public WebClient webClient() {
		
		return WebClient.builder().baseUrl("https://generativelanguage.googleapis.com").defaultHeader("x-goog-api-key", apiKey).build();
	}
}
