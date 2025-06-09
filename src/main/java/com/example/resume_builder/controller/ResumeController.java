package com.example.resume_builder.controller;


import com.example.resume_builder.entity.Resume;
import com.example.resume_builder.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResumeController {

    @Autowired
    ResumeService resumeService;

    @PostMapping("/addresume")
    public ResponseEntity<Resume> saveResume(@RequestBody Resume resume){
        return ResponseEntity.ok(resumeService.saveResume(resume));
    }

    @GetMapping("/resumes")
    public ResponseEntity<List<Resume>> getAllResumes(){
        return ResponseEntity.ok(resumeService.getAllResumes());
    }

    @GetMapping("/resume/{id}")
    public ResponseEntity<Resume> getResumeById(@PathVariable Long id){
        return resumeService.getResumeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
