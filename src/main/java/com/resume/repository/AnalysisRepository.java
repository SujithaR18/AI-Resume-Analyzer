package com.resume.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resume.model.Analysis;

public interface AnalysisRepository extends JpaRepository<Analysis, Long> {

	Optional<Analysis> findByResumeNameAndJobDescription(String resumeName, String jobDescription);
}
