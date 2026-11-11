package com.resume.util;

import java.util.*;
import java.util.stream.Collectors;

public class SkillFilter {

	private static final Set<String> VALID_SKILLS = Set.of(
			
			 // Java
            "Java", "J2EE", "Spring", "Spring Boot", "Hibernate", "JPA",
            "Servlet", "JSP", "Maven", "Gradle",

            // Database
            "SQL", "MySQL", "Oracle", "PostgreSQL", "MongoDB", "SQLite",

            // Frontend
            "HTML", "HTML5",
            "CSS", "CSS3",
            "JavaScript", "TypeScript",
            "React", "Angular", "AngularJS", "Vue.js", "Bootstrap",

            // Backend
            "REST API", "REST APIs", "Microservices",
            "Node.js", "Express.js",

            // Cloud & DevOps
            "AWS", "Azure", "GCP",
            "Docker", "Kubernetes",
            "Jenkins", "Git", "GitHub",

            // Programming
            "Python", "C", "C++", "C#", "PHP",

            // AI / ML
            "Machine Learning", "Deep Learning",
            "OpenCV", "MediaPipe", "TensorFlow", "PyTorch",

            // Testing
            "JUnit", "Selenium", "TestNG",
            "JUnit5", "Mockito",

            // Tools
            "Eclipse", "IntelliJ IDEA",
            "VS Code", "Postman",

            // Messaging
            "Kafka", "RabbitMQ",

            // OS
            "Linux", "Windows",

            // Other
            "Cloud Computing"
			);
	
	private static final Set<String> VALID_SKILLS_LOWER = VALID_SKILLS.stream().map(String::toLowerCase).collect(Collectors.toSet());
	
	public static List<String> filterSkills(List<String> skills){
		
		if (skills == null) {
			return Collections.emptyList();
		}
		
		return skills.stream().map(String::trim).filter(skill -> VALID_SKILLS_LOWER.contains(skill.toLowerCase())).distinct().sorted().collect(Collectors.toList());
	}
}
