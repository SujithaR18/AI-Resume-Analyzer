package com.resume.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resume.model.JobMatchResult;
import com.resume.prompt.JobMatchPrompt;

@Service
public class JobMatchService {

	@Autowired
	private GeminiService geminiService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Async("resumeTaskExecutor")
	public CompletableFuture<JobMatchResult> analyzeJobMatch(String resumeText, String jobDescription) {
		
		try {
			String prompt = JobMatchPrompt.getPrompt(resumeText, jobDescription);
			
			String response = geminiService.generateContent(prompt);
			
			JobMatchResult jobResult = objectMapper.readValue(response, JobMatchResult.class);
			
			return CompletableFuture.completedFuture(jobResult);
			
		} catch (Exception e) {
			
			throw new RuntimeException("Unable to fetch job match analysis", e);
		}
	}
}
