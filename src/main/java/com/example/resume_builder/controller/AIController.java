package com.example.resume_builder.controller;

import com.example.resume_builder.service.QnAService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/qna")
public class AIController {

    private final QnAService qnAService;

    @PostMapping("/project")
    public ResponseEntity<String> generateProjectDesc(@RequestBody Map<String, String> payload){
        String question = payload.get("question");
        String answer = qnAService.generateProjectDesc(question);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/experience")
    public ResponseEntity<String> generateExperienceDesc(@RequestBody Map<String, String> payload){
        String question = payload.get("question");
        String answer = qnAService.generateExperienceDesc(question);
        return ResponseEntity.ok(answer);
    }



}
