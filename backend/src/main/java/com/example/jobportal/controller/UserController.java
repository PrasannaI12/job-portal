package com.example.jobportal.controller;

import com.example.jobportal.dto.UpdateUserRequestDto;
import com.example.jobportal.dto.UserCreationDto;
import com.example.jobportal.dto.UserResponseDto;
import com.example.jobportal.entity.User;
import com.example.jobportal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getCurrentUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.getUser(user.getId()));
    }

    @PutMapping("/me")
    public ResponseEntity<UpdateUserRequestDto> updateCurrentUser(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody UpdateUserRequestDto dto) {
        return ResponseEntity.ok(userService.updateUser(dto, user.getId()));
    }
}