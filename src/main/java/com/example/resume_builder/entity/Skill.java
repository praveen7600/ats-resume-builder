package com.example.resume_builder.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String level; // Beginner, Intermediate, Advanced

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

    // Getters and setters
}
