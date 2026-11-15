package com.example.jobportal.dto;

import com.example.jobportal.entity.Role;

public record UserResponseDto(
        String token,
        Long id,
        String fullName,
        String email,
        Long mobileNumber,
        Role role
) {}
