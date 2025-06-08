package com.example.resume_builder.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<Experience> experiences;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<Education> educationList;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<Skill> skills;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<Certification> certifications;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<Project> projects;

    // Getters and setters
}
