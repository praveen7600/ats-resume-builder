package com.example.resume_builder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Skill name is required")
    private String name;

    private String level; // Beginner, Intermediate, Advanced

    @ManyToOne
    @JoinColumn(name = "resume_id")
    @JsonBackReference

    private Resume resume;

    // Getters and setters
}
