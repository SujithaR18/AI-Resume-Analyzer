package com.resume.service;

import org.springframework.stereotype.Service;

@Service
public class JobMatchCalculator {

	public String calculateVerdict(int matchPercentage) {
		
		if (matchPercentage >= 85) {
			
			return "Excellent Match";
		}
		
		if (matchPercentage >= 70) {
			
			return "Strong Match";
		}
		
		if (matchPercentage >= 50) {
			
			return "Moderate Match";
		}
		
		return "Low Match";
	}
	
}
