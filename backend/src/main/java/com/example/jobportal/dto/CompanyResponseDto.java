package com.example.jobportal.dto;

public record CompanyResponseDto(
        Long id,
        String companyName,
        String companyOverview,
        String headquarters,
        String website,
        int employees,
        String location
) {
}
