package com.resume.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JobMatchResult {

	private int matchPercentage;
	private List<String> matchedSkills;
	private List<String> missingSkills;
	private List<String> suggestions;
	private String verdict;

}
