package com.resume.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resume.model.ATSResult;
import com.resume.model.Analysis;
import com.resume.model.AnalysisResult;
import com.resume.model.JobMatchResult;
import com.resume.repository.AnalysisRepository;
import com.resume.service.*;

@Controller
public class ResumeController {
	
	@Autowired
	private PdfService pdfService;
	
	@Autowired
	private ScoreCalculator scoreCalculator;
	
	@Autowired
	private AnalysisService analysisService;
	
	@Autowired
	private ATSService atsService;
	
	@Autowired
	private JobMatchService jobMatchService;
	
	@Autowired
	private JobMatchCalculator jobMatchCalculator;
	
	@Autowired
	private AnalysisRepository analysisRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@GetMapping("/")
	public String home() {
		return "upload";
	}
	
	@PostMapping("/analyze")
	public String analyze(@RequestParam("resume") MultipartFile file,@RequestParam("jobDescription") String jobDescription , Model model) {
		
		// Step 1 : Extract text from uploaded PDF
		String resumeText = pdfService.extractText(file);
		
		// Step-2: Checking duplicate BEFORE Gemini API call
		String resumeName = file.getOriginalFilename();
		
		Optional<Analysis> existingAnalysis = analysisRepository.findByResumeNameAndJobDescription(resumeName, jobDescription);
		
		if (existingAnalysis.isPresent()) {
			
			Analysis existing = existingAnalysis.get();
			
			// Fetching the existed data from database, to add it to the result page
			AnalysisResult resume = new AnalysisResult();
			
			// for User Information
			resume.setName(existing.getCandidateName());
			resume.setDegree(existing.getDegree());
			resume.setEmail(existing.getEmail());
			resume.setPhone(existing.getPhone());
			resume.setExperience(existing.getExperience());
			resume.setLinkedIn(existing.getLinkedIn());
			resume.setGithub(existing.getGithub());
			
			// for ATS Score
			ATSResult ats = new ATSResult();
			ats.setCalculatedScore(existing.getAtsScore());
			
//			try {
//				ats.setSuggestions(objectMapper.readValue(existing.getSuggestions(), new TypeReference<List<String>>() {}));
//				
//			} catch (JsonProcessingException e) {
//				
//				throw new RuntimeException("Failed to convert the suggestion into JSON");
//			}
//			
			// for Job Match
			JobMatchResult jobMatch = new JobMatchResult();
			jobMatch.setMatchPercentage(existing.getMatchPercentage());
			jobMatch.setVerdict(existing.getVerdict());
			
			// For Converting the List type of Data
			try {
			jobMatch.setMatchedSkills(objectMapper.readValue(existing.getMatchedSkills(), new TypeReference<List<String>>() {}));
			
			jobMatch.setMissingSkills(objectMapper.readValue(existing.getMissingSkills(), new TypeReference<List<String>>() {}));
			
			jobMatch.setSuggestions(objectMapper.readValue(existing.getSuggestions(), new TypeReference<List<String>>() {}));
			
			} catch (JsonProcessingException e) {
				
				throw new RuntimeException("Failed to convert skills into JSON");
			}
			
			
			model.addAttribute("resume", resume);
			model.addAttribute("jobMatch", jobMatch);
			model.addAttribute("ats", ats);
			
			System.out.println("Duplicate analysis found. Returning existing data without calling Gemini API");
			
			
			return "result";
		}
		
		// If not, Step 3, 4 and 5 Extraction : Run AI analysis in parallel, so it will 3 API calls
		CompletableFuture<AnalysisResult> resumeFuture = analysisService.analyzeResume(resumeText);
		
		CompletableFuture<ATSResult> atsFuture = atsService.analyzeResult(resumeText);
		
		CompletableFuture<JobMatchResult> jobMatchFuture = jobMatchService.analyzeJobMatch(resumeText, jobDescription);
		
		// Wait for all AI operations to finish
		CompletableFuture.allOf(resumeFuture, atsFuture, jobMatchFuture).join();
		
		// Get Results
		// Step 3 :extracted text
		AnalysisResult resume = resumeFuture.join();
		
		// Step-4: ATS Analysis
		ATSResult ats = atsFuture.join();
		
		// Step-5: Job Match Analysis and Match Verdict
		JobMatchResult jobMatch = jobMatchFuture.join();
		String verdict = jobMatchCalculator.calculateVerdict(jobMatch.getMatchPercentage());
		
		jobMatch.setVerdict(verdict);
		
		// Step-6: Calculate ATS Score and Rating.
		int score = scoreCalculator.calculateOverallScore(ats);
		String rating = scoreCalculator.calculateRating(score);
		
		// ATS Score and Rating
		ats.setCalculatedScore(score);
		ats.setRating(rating);
		
		// Adding data to Database
		Analysis analysis = new Analysis();
		
		analysis.setResumeName(file.getOriginalFilename());
		analysis.setCandidateName(resume.getName());
		analysis.setDegree(resume.getDegree());
		analysis.setEmail(resume.getEmail());
		analysis.setPhone(resume.getPhone());
		analysis.setExperience(resume.getExperience());
		analysis.setLinkedIn(resume.getLinkedIn());
		analysis.setGithub(resume.getGithub());
		analysis.setJobDescription(jobDescription);
		analysis.setMatchPercentage(jobMatch.getMatchPercentage());
		analysis.setAtsScore(ats.getCalculatedScore());
		analysis.setVerdict(jobMatch.getVerdict());
		
		// For Converting the List type of Data
		try {
			
		analysis.setMatchedSkills(objectMapper.writeValueAsString(jobMatch.getMatchedSkills()));
		
		analysis.setMissingSkills(objectMapper.writeValueAsString(jobMatch.getMissingSkills()));
		
		analysis.setSuggestions(objectMapper.writeValueAsString(jobMatch.getSuggestions()));
		
		} catch (JsonProcessingException e) {
			
			throw new RuntimeException("Failed to convert Data to JSON", e);
		}
		// to save the Data
		analysisRepository.save(analysis);
		System.out.println("New analysis saved to database.");
		
		// Step 5 : Send data to HTML
		model.addAttribute("resume", resume);
		model.addAttribute("jobMatch", jobMatch);
		model.addAttribute("ats", ats);
		
		return "result";
	}
}
