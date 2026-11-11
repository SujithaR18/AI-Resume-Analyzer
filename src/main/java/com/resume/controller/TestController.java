package com.resume.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.resume.model.ATSResult;
import com.resume.model.AnalysisResult;
import com.resume.service.ATSService;
import com.resume.service.AnalysisService;
import com.resume.service.GeminiService;
import com.resume.service.PdfService;

@RestController
public class TestController {

	@Autowired
	private AnalysisService analysisService;
	
	@Autowired
	private PdfService pdfService;
	
	@Autowired
	private ATSService atsService;
	
	@GetMapping("/test-ai")
	public CompletableFuture<AnalysisResult> testAI() {
		
		String sampleResume = """
				Radhey Shyam
				radheyshyam@gmail.com
				9876543210
				
				
				Bachelor of Computer Application
				
				Skills:
				Java
				Spring Boot
				MySQL
				
				Project:
				AI Virtual Mouse
				
				Experience:
				Fresher
				""";
		
		return analysisService.analyzeResume(sampleResume);
	}
	
	@PostMapping("/test-ats")
	@ResponseBody
	public CompletableFuture<ATSResult> testATS(@RequestParam("resume") MultipartFile file) throws Exception {
		
		String resumeText = pdfService.extractText(file);
		
		return atsService.analyzeResult(resumeText);
	}
}
