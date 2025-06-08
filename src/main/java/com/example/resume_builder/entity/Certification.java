package com.example.resume_builder.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String organization;
    private String dateIssued;
    private String expiryDate;
    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

    // Getters and setters
}

