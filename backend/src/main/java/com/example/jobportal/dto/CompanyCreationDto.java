package com.example.jobportal.dto;

import jakarta.validation.constraints.NotBlank;

public record CompanyCreationDto(
        @NotBlank(message = "Company name is required")
        String companyName,
        String companyOverview,
        String headquarters,
        String website,
        int employees,
        String location
) {
}
