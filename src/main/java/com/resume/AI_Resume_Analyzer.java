package com.resume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AI_Resume_Analyzer {

	public static void main(String[] args) {
		
		System.setProperty("java.net.preferIPv4Stack", "true");
		
		SpringApplication.run(AI_Resume_Analyzer.class, args);
	}

}
