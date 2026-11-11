package com.resume.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.resume.model.ATSResult;

@Service
public class ScoreCalculator {
	
	private static final double SKILL_WEIGHT = 0.35;
	private static final double PROJECT_WEIGHT = 0.20;
	private static final double EDUCATION_WEIGHT = 0.20;
	private static final double EXPERIENCE_WEIGHT = 0.15;
	private static final double FORMATTING_WEIGHT = 0.10;

	public int calculateOverallScore(ATSResult ats) {
		
		double score = ats.getSkillScore() * SKILL_WEIGHT + ats.getProjectScore() * PROJECT_WEIGHT + ats.getEducationScore() * EDUCATION_WEIGHT + ats.getExperienceScore() * EXPERIENCE_WEIGHT + ats.getFormattingScore() * FORMATTING_WEIGHT;
		
		return (int) Math.round(score);
	}
	
	public String calculateRating(int score) {
		
		if (score >= 90) {
			return "Excellent";
		}
		
		if (score >= 75) {
			return "Very Good";
		}
		
		if (score >= 60) {
			return "Good";
		}
		
		if (score >= 40) {
			return "Needs Improvement";
		}
		
		return "Poor";
	}
}
