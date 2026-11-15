package com.example.jobportal.dto;

import com.example.jobportal.entity.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateApplicationStatusDto(
        @NotNull(message = "Status cannot be null")
        ApplicationStatus status
) {
}
