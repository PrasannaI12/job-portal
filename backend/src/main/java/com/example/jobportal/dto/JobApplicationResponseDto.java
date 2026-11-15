package com.example.jobportal.dto;

import com.example.jobportal.entity.ApplicationStatus;

import java.time.LocalDateTime;

public record JobApplicationResponseDto(
        Long id,
        Long jobId,
        String jobTitle,
        String companyName,
        ApplicationStatus status,
        LocalDateTime appliedAt
) {
}
