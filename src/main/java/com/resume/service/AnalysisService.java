package com.resume.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resume.model.AnalysisResult;
import com.resume.prompt.ResumePrompt;
import com.resume.util.SkillFilter;

@Service
public class AnalysisService {
	
	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private GeminiService geminiService;
	
	@Async("resumeTaskExecutor")
	public CompletableFuture<AnalysisResult> analyzeResume(String resumeText) {
		
		try {
			String prompt = ResumePrompt.buildResumePrompt(resumeText);
		
			String json = geminiService.generateContent(prompt);
		
			AnalysisResult resume = mapper.readValue(json, AnalysisResult.class);
			
			resume.setSkills(SkillFilter.filterSkills(resume.getSkills()));
		
			return CompletableFuture.completedFuture(resume);
			
		} catch (Exception e) {

			throw new RuntimeException("Unable to parse AI Response.", e);
		}
	}
}
