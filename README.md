# AI Resume Analyzer 🚀

An AI-powered Resume Analyzer built using **Java, Spring Boot, MySQL, and Google Gemini API** to evaluate resumes, generate ATS analysis, and identify how well a candidate's skills match a given job description.

The application helps job seekers understand their resume strengths, identify skill gaps, and improve their chances of getting shortlisted.

## ✨ Features

- **Resume Parsing:** Extracts relevant information from uploaded PDF resumes.
- **AI-Powered ATS Analysis:** Uses Google Gemini API to analyze resume content and provide ATS-related feedback.
- **Job Description Matching:** Compares resume skills with the requirements of a given job description.
- **Match Score:** Calculates a job compatibility score based on matching skills.
- **Matched & Unmatched Skills:** Highlights skills that match the job requirements and skills that are missing.
- **Improvement Suggestions:** Provides concise suggestions to improve job relevance.
- **Database Integration:** Stores resume analysis results using MySQL.
- **AI Integration:** Uses Gemini 2.5 Flash for intelligent resume analysis.

## 🛠️ Technologies Used

| Category | Technology |
|---|---|
| Backend | Java |
| Framework | Spring Boot |
| AI Integration | Google Gemini API |
| Database | MySQL |
| Frontend | HTML, CSS, JavaScript |
| Template Engine | Thymeleaf |
| Build Tool | Maven |
| Version Control | Git & GitHub |

## 🏗️ Project Architecture

The application follows a layered architecture to maintain separation of concerns.

- **Controller:** Handles HTTP requests and application responses.
- **Service:** Implements business logic and coordinates application operations.
- **Repository:** Handles database operations using Spring Data JPA.
- **Model:** Represents application entities and data structures.
- **DTO:** Transfers data between application layers and external APIs.
- **Config:** Contains application configuration, including Gemini API integration.
- **Util:** Provides utility functions for resume normalization and skill filtering.
- **Prompt:** Manages prompts used for AI-powered analysis.

## ⚙️ How It Works

1. User uploads a PDF resume.
2. The application extracts relevant resume information.
3. The user provides a job description.
4. Resume content and job requirements are processed.
5. Google Gemini API generates AI-powered ATS analysis.
6. The application evaluates job-related skill matching.
7. Results are displayed with match scores, matched skills, unmatched skills, and improvement suggestions.
8. Analysis results are stored in the MySQL database.

## 💻 Getting Started

### Prerequisites

Make sure you have installed:

- Java 17 or later (compatible with your Spring Boot configuration)
- MySQL Server
- Maven
- Git
- Google Gemini API Key

### 1. Clone the Repository

```bash
git clone https://github.com/SujithaR18/AI-Resume-Analyzer.git
```

### 2. Navigate to the Project Directory

```bash
cd AI-Resume-Analyzer
```

### 3. Configure the Database

Create a MySQL database:

```sql
CREATE DATABASE resume_analyzer;
```

Update your `application.properties` with your database configuration.

### 4. Configure Gemini API

Generate an API key through Google AI Studio.

Configure your application using environment variables instead of hardcoding credentials.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/resume_analyzer?createDatabaseIfNotExist=true
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

gemini.api.key=${GEMINI_API_KEY}
gemini.model=gemini-2.5-flash
```

Set the required environment variables before starting the application.

### 5. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Alternatively, use the Maven wrapper:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

Open the application in your browser using the local URL configured by Spring Boot (default: `http://localhost:8080`).

## 🔮 Future Enhancements

- Support for additional resume formats.
- Integration with more AI models.
- Advanced job recommendation functionality.
- User authentication and profile management.
- Resume history and comparison.
- Cloud deployment and improved scalability.

## 🎯 Project Objective

The main objective of this project is to simplify resume evaluation through AI and help job seekers understand how effectively their resumes align with specific job requirements.

It also demonstrates practical implementation of Java backend development, REST API integration, database management, and AI-powered application development.

## 👩‍💻 Developer

**Sujitha R**

GitHub: [SujithaR18](https://github.com/SujithaR18)

Project Repository: [AI Resume Analyzer](https://github.com/SujithaR18/AI-Resume-Analyzer)

---

⭐ If you find this project interesting, consider giving the repository a star!
