package com.example.resume_builder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Degree name is required")
    private String degree;
    @NotBlank(message = "College name is required")
    private String institution;

    private String startYear;
    @NotBlank(message = "End year is required")
    private String endYear;

    private String grade;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    @JsonBackReference
    private Resume resume;

    // Getters and setters
}

