package com.resume.prompt;

public class ATSPrompt {

	public static String buildATSPrompt(String resumeText) {
		
		return """
				
		You are an expert Applicant Tracking System (ATS).
		
		Analyze the following resume as if it is being screened by an ATS.
		
		Return ONLY valid JSON.
		  
		Do NOT add explanations.
		Do NOT use markdown.
		Do NOT write ```json.
		
		Do NOT calculate the overall ATS score.
		Use this exact JSON structure:
		
		{
			
			"skillScore":0,
			"educationScore":0,
			"projectScore":0,
			"experienceScore":0,
			"formattingScore":0,
			"strengths":[],
			"weaknesses":[],
			"suggestions":[]
			
		}
		
		Evaluation Rules:
			1. All scores must be between 0 and 100.
			2. Evaluate skills based on relevance and variety.
			3. Evaluate education based on degree and academic details.
			4. Evaluate projects based on quality, technologies and practical experience.
			5. Evaluate experience based on internships, jobs and achievements.
			6. Evaluate formatting based on ATS readability and organization.
			7. Overall score should reflect the complete resume.
			
		Strengths:
			1. Maximum 5 items
			2. Maximum 4-5 words each

		Weaknesses:
			1. Maximum 5 items
			2. Maximum 4-5 words each

		Suggestions:
			1. Maximum 5 items
			2. Maximum 5 words each
			
		Suggestions should consider:
			1. If GitHub is missing, recommend adding it
			2. If LinkedIn is missing, recommend adding it
			3. If internship is missing, recommend gaining experience
			4. Missing certifications
			5. Missing Spring Boot/Hibernate/REST API skills (if applicable)
			6. Weak project portfolio
			7. ATS formatting improvements
		
		Do not explain.
		Do not write paragraphs.
		Only include suggestions that are relevant to the uploaded resume.
		Do not suggest something that already exists in the resume.
		
		Resume:
		
		%s
		""".formatted(resumeText);
			
	}
}
