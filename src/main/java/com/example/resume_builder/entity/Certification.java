package com.example.resume_builder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Certification name is required")
    private String title;
    private String organization;
    private String dateIssued;
    private String expiryDate;
    @ManyToOne
    @JoinColumn(name = "resume_id")
    @JsonBackReference

    private Resume resume;

    // Getters and setters
}

