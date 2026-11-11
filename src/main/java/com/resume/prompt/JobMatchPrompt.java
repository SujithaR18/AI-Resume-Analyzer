package com.resume.prompt;

public class JobMatchPrompt {

	public static String getPrompt(String resumeText, String jobDescription) {

		return """
					You are an ATS Resume Matching Expert.

					Compare the following resume with the given Job Description.

					Return ONLY valid JSON

					Do NOT add explanations.
					Do NOT use markdown.
					Do NOT write ```json.

					Return exactly in the format:

					{
						"matchPercentage":0,
						"matchedSkills":[],
						"missingSkills":[],
						"suggestions":[]
					}

					Rules:
						1. matchPercentage must be between 0 and 100.
						2. matchSkills should contain only skills found in resume and job description.
						3. Consider equivalent technologies as matching.
							Example:
								- Core Java = Java
								- HTML5 = HTML
								- CSS3 = CSS
								- Oracle SQL = SQL
						4. Ignore differences in uppercase/lowercase.
						5. Ignore duplicate skills.
						6. Match skills based on meaning, not exact wording.
						7. Do not invent skills, that are not present.
						8. missingSkills should contain skills required in the job description, but missing from the resume.
					Suggestions:
						1. Maximum 5 items
						2. Maximum 5 words each

					Suggestions should consider:
						1. If GitHub is missing, recommend adding it
						2. If LinkedIn is missing, recommend adding it
						3. If internship is missing, recommend gaining experience
						4. Missing certifications
						5. Missing Spring Boot/Hibernate/REST API skills (if applicable)
						6. Weak project portfolio.

					Do not explain.
					Do not write paragraphs.
					Only include suggestions that are relevant to the uploaded resume.
					Do not suggest something that already exists in the resume.

					Resume:

					%s

					Job Description:

					%s
						"""
				.formatted(resumeText, jobDescription);
	}
}
