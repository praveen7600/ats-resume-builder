package com.example.resume_builder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Profession name is required")
    private String profession;

    @NotBlank(message = "Company name is required")
    private String companyName;

    private String startDate;
    private String endDate;

    @ElementCollection
    @CollectionTable(name = "experience_responsibilities", joinColumns = @JoinColumn(name = "experience_id"))
    @Column(name = "responsibilities", columnDefinition = "TEXT")
    private List<String> responsibilities;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    @JsonBackReference
    private Resume resume;

    // Getters and setters
}


