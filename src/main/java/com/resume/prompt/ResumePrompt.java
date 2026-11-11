package com.resume.prompt;

public class ResumePrompt {
	
	private ResumePrompt() {
		// to prevent object creation.
	}

	public static String buildResumePrompt(String resumeText) {
		
		return """
				You are an expert ATS Resume Analyzer.

				Analyze the Resume carefully and return ONLY the valid JSON.
				
				Do not add explanations.
				
				Do not use markdown.
				
				Do not wrap the JSON inside ```.
				
				Return exactly this format:
				
				{
				"name":"",
				"degree":"",
				"email":"",
				"phone":"",
				"linkedIn":"",
				"github":"",
				"skills":[],
				"projects":[
					{
						"title":"",
						"technologies":"",
						"duration":""
					}
				],
				"education":[
					{
						"degree":"",
						"college":"","
						"year":"",
						"score":""
					}
				],
				"experience:""
				}
				
				Rules:
				
				1. JSON keys MUST be lowercase.
				2. Skills must be an array of Strings and for "skills", return ONLY skill names.
				3. Extract ONLY the primary technical skills.
					Do NOT include:
						- Topics
						- Concepts
						- Chapters
						- Syntax
						- Language features
						- Individual APIs
						- Beginner learning modules 
						
					Example-1:
						Resume contains

						Java
						Loops
						Arrays
						Methods
						Constructors
						OOPs

						Return

						Java
					
					Example-2:
						Resume contains

							HTML5
							Forms
							Tables
							Images
							Hyperlinks
							Semantic HTML

							Return

							HTML5
				4. For "projects", return only project names in "title".
				5. Put technologies used in each project.
				6. Include project duration if available.
				7. Extract all education details.
				8. If no work experience exists, return: "experience":"Fresher"
				9. Never return null.
				10. If LinkedIn is not present in the resume, return "Not Found".
				11. If GitHub is not present in the resume, return "Not Found".
				
				Resume:
				
				
				%s
				""".formatted(resumeText);
	}
}