package com.example.jobportal.dto;

import com.example.jobportal.entity.EmploymentType;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record JobPostCreationDto(
        @NotBlank(message = "Title is required")
        String title,
        String description,
        List<String> requiredSkills,
        EmploymentType employmentType,
        String education,
        Integer salary,
        String location
) {
}
