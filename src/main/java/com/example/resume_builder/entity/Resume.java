package com.example.resume_builder.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone no is required")
    private String phone;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Experience> experiences;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Education> educationList;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Skill> skills;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Certification> certifications;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Project> projects;


    // Getters and setters
}
