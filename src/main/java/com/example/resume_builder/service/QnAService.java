package com.example.resume_builder.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class QnAService {
    // Access to APIKey and URL [Gemini]
    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final WebClient webClient;

    public QnAService(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }

    public String generateProjectDesc(String question) {
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", """
                    You are an expert resume strategist specializing in ATS-compliant resumes. Given the project description below, write **exactly 3** high-impact resume bullet points that:

                    - Start with powerful action verbs (e.g., Designed, Implemented, Led)
                    - Showcase measurable outcomes (e.g., 25% increase, saved 10 hours/week)
                    - Mention specific tools, languages, frameworks, or platforms used
                    - Highlight the candidate’s initiative, problem-solving, or leadership
                    - Use concise, keyword-rich, professional language for maximum ATS score
                    - Avoid introductory statements or explanations

                    Output ONLY the 3 bullet points in plain text, without headings or markdown.

                    Project Description: """ + question)
                        })
                }
        );

        String response = webClient.post()
                .uri(geminiApiUrl + geminiApiKey)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
    }

    public String generateExperienceDesc(String question) {
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", """
                    You are a top-tier resume writer skilled in optimizing professional experience for ATS systems. Based on the following experience description, write **3 to 5** bullet points that:

                    - Begin with strong, varied action verbs (e.g., Streamlined, Developed, Led)
                    - Quantify results clearly (e.g., reduced processing time by 40%, increased accuracy by 15%)
                    - Include relevant technologies, systems, or methodologies used (e.g., Java, Spring Boot, Agile)
                    - Connect daily tasks to tangible business outcomes or user impact
                    - Avoid fluff, pronouns, and explanations — keep it concise, formal, and impactful

                    Format: Only output bullet points in plain text — no intro lines, no markdown.

                    Experience Description: """ + question)
                        })
                }
        );

        String response = webClient.post()
                .uri(geminiApiUrl + geminiApiKey)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
    }

}
