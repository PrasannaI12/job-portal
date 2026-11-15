package com.example.jobportal.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequestDto(
        @NotBlank(message = "Full name cannot be blank")
        String fullName,
        Long mobileNumber
) {
}
