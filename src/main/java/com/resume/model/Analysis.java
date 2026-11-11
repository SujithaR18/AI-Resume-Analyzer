package com.resume.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Analysis {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String resumeName;
	private String candidateName;
	private String email;
	private String phone;
	private String degree;
	private String experience;
	
	@Lob
	private String linkedIn;
	
	@Lob
	private String github;
	
	@Lob
	private String jobDescription;
	
	@Lob
	private String matchedSkills;
	
	@Lob
	private String missingSkills;
	
	@Lob
	private String suggestions;
	
	private Integer matchPercentage;
	private String verdict;
	private Integer atsScore;
	
}
