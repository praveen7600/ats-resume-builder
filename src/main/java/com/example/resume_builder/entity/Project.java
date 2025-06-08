package com.example.resume_builder.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String technology;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

    // Getters and setters
}

