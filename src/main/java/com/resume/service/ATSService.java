package com.resume.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resume.model.ATSResult;
import com.resume.prompt.ATSPrompt;

@Service
public class ATSService {

	@Autowired
	private GeminiService geminiService;
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Async("resumeTaskExecutor")
	public CompletableFuture<ATSResult> analyzeResult(String resumeText) {
		
		try {
			String prompt = ATSPrompt.buildATSPrompt(resumeText);

			String json = geminiService.generateContent(prompt);
			
			ATSResult atsResult = objectMapper.readValue(json, ATSResult.class);

			return CompletableFuture.completedFuture(atsResult);
			
		} catch (Exception e) {

			throw new RuntimeException("Failed to generate ATS analysis.", e);
		}
	}
}
