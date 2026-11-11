package com.resume.service;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class SkillMatcher {
	private static final List<String> SKILLS  = Arrays.asList("java", "spring", "html", "css", "javascript", "mysql", "react", "python", "sql", "hibernate");
	
	public List<String> extractMatchedSkills(String text){
		List<String> found = new ArrayList<>();
		
		text = text.toLowerCase();
		
		for(String skill : SKILLS) {
			if(text.contains(skill)) {
				found.add(skill);
			}
		}
		
		return found;
	}
	
	public List<String> extractMissingSkills(List<String> resumeSkills, List<String> jobSkills){
		List<String> missingSkills = new ArrayList<>();
		
		for(String skill : jobSkills) {
			if(!resumeSkills.contains(skill.toLowerCase())) {
				missingSkills.add(skill);
			}
		}
		
		return missingSkills;
	}
}
