package com.example.resume_builder.service;

import com.example.resume_builder.entity.Resume;
import com.example.resume_builder.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ResumeService {

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    QnAService aiService;

    private final ObjectMapper mapper = new ObjectMapper();

    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }

    public Resume saveResume(Resume resume) {

        // For each Experience: generate AI descriptions for responsibilities as one text blob
        resume.getExperiences().forEach(exp -> {
            exp.setResume(resume);
            // Join all responsibilities into a single string prompt for AI
            String responsibilitiesText = String.join(". ", exp.getResponsibilities());

            // Call AI service to generate bullet points (JSON response)
            String aiJsonResponse = aiService.generateExperienceDesc(responsibilitiesText);

            // Extract text from AI JSON response
            String aiFullText = extractTextFromAIResponse(aiJsonResponse);

            // Split into bullet points (List<String>)
            List<String> aiResponsibilities = Arrays.stream(aiFullText.split("\\r?\\n"))
                    .filter(line -> !line.isBlank())
                    .map(String::trim)
                    .collect(Collectors.toList());

            exp.setResponsibilities(aiResponsibilities);
        });

        // For each Project: generate AI description based on project.description
        resume.getProjects().forEach(proj -> {
            proj.setResume(resume);

            // Call AI service with project description text (JSON response)
            String aiJsonResponse = aiService.generateProjectDesc(proj.getDescription());

            // Extract full text from AI JSON response
            String projectDescription = extractTextFromAIResponse(aiJsonResponse);

            proj.setDescription(projectDescription.trim());
        });

        // Set resume for other child entities to maintain cascading
        resume.getEducationList().forEach(ed -> ed.setResume(resume));
        resume.getSkills().forEach(s -> s.setResume(resume));
        resume.getCertifications().forEach(c -> c.setResume(resume));

        return resumeRepository.save(resume);
    }

    public Optional<Resume> getResumeById(Long id) {
        return resumeRepository.findById(id);
    }

    /**
     * Extracts concatenated text from the AI JSON response,
     * looking into candidates[0].content.parts[].text
     * Returns raw response on failure.
     */
    private String extractTextFromAIResponse(String aiJsonResponse) {
        try {
            JsonNode root = mapper.readTree(aiJsonResponse);
            JsonNode candidates = root.path("candidates");
            if (candidates.isArray() && candidates.size() > 0) {
                JsonNode content = candidates.get(0).path("content");
                JsonNode parts = content.path("parts");
                if (parts.isArray()) {
                    StringBuilder fullText = new StringBuilder();
                    for (JsonNode part : parts) {
                        fullText.append(part.path("text").asText());
                    }
                    return fullText.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // fallback: return raw response if JSON parse fails
        return aiJsonResponse;
    }
}
