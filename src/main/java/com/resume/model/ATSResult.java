package com.resume.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ATSResult {
	
	private int calculatedScore;
	private int skillScore;
	private int educationScore;
	private int projectScore;
	private int experienceScore;
	private int formattingScore;
	private List<String> strengths;
	private List<String> weaknesses;
	private List<String> suggestions;
	private String rating;

}
