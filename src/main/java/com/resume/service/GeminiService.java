package com.resume.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.resume.dto.Content;
import com.resume.dto.GeminiRequest;
import com.resume.dto.GeminiResponse;
import com.resume.dto.Part;

@Service
public class GeminiService {

	@Autowired
	private WebClient webClient;
	
	@Value("${gemini.model}")
	private String model;
	
	public String generateContent(String prompt) {
		
		Part part = new Part(prompt);
		
		Content content = new Content(List.of(part));
		
		GeminiRequest request = new GeminiRequest(List.of(content));
		
//		for testing purpose:
		
//		System.out.println("Calling Gemini...");
		
		GeminiResponse response = webClient.post().uri("/v1beta/models/" + model + ":generateContent").bodyValue(request).retrieve().bodyToMono(GeminiResponse.class).block();
		
//		System.out.println("Gemini Response Received");
		
		return response.getCandidates().get(0).getContent().getParts().get(0).getText();
	}
}
