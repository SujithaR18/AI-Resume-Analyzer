package com.resume.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AnalysisResult {
	private String name;
	private String degree;
	private String email;
	private String phone;
	private String linkedIn;
	private List<Education> education;
	private List<String> skills;
	private List<Project> projects;
	private String experience;
	private String github;
	
}
