package com.example.jobportal.service;

import com.example.jobportal.dto.LoginRequestDto;
import com.example.jobportal.dto.UpdateUserRequestDto;
import com.example.jobportal.dto.UserCreationDto;
import com.example.jobportal.dto.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(UserCreationDto dto);

    UserResponseDto loginUser(LoginRequestDto dto);

    UserResponseDto getUser(Long userId);

    UpdateUserRequestDto updateUser(UpdateUserRequestDto dto, Long userId);
}
