package com.resume.util;

public class ResumeNormalizer {

	public static String normalize(String text) {
		if (text == null || text.isEmpty()) {
			return "";
		}
		
		// Remove unwanted unicode characters
		text = text.replace("\u00A0", " "); // Non-breaking space
		text = text.replace("\u2022", " "); //Bullet
		text = text.replace("\u25CF", " "); // Black Circle
		text = text.replace("\u25AA", " "); // Square
		text = text.replace("\u25E6", " "); // Circle
		
		// Fix Apostrophe issues
		text = text.replace("\u2018", "'");
		text = text.replace("\u2019", "'");
		text = text.replace("`", "'");
		
		// Fix Quotes
		text = text.replace("\u201C", "/");
		text = text.replace("\u201D", "/");
		
		// Replace Long Slash
		text = text.replace("\u2013", "-");
		text = text.replace("\u2014", "-");
		
		// Replace Tabs with space
		text = text.replace("\t", " ");
		
		// Replace multiple spaces
		text = text.replace("[ ]{2,}", " ");
		
		// Replace multiple blank lines
		text = text.replace("(\\r?\\n){2,}", "\n");
		
		// Trim
		text = text.trim();
		
		return text;
		
	}
}
