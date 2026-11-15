package com.example.jobportal.dto;

import com.example.jobportal.entity.ApplicationStatus;

import java.time.LocalDateTime;

public record EmployerApplicationResponseDto(
        Long applicationId,
        Long jobId,
        String jobTitle,
        Long seekerId,
        String candidateName,
        String candidateEmail,
        Long candidateMobileNumber,
        ApplicationStatus status,
        LocalDateTime appliedAt
) {
}
