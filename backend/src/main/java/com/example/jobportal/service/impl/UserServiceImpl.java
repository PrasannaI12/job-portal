package com.example.jobportal.service.impl;

import com.example.jobportal.dto.LoginRequestDto;
import com.example.jobportal.dto.UpdateUserRequestDto;
import com.example.jobportal.dto.UserCreationDto;
import com.example.jobportal.dto.UserResponseDto;
import com.example.jobportal.entity.User;
import com.example.jobportal.exception.BadRequestException;
import com.example.jobportal.exception.ResourceNotFoundException;
import com.example.jobportal.mapper.UserMapper;
import com.example.jobportal.mapper.UserUpdateMapper;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.security.JwtService;
import com.example.jobportal.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final UserUpdateMapper updateMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper mapper,
                           UserUpdateMapper updateMapper,
                           JwtService jwtService,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.updateMapper = updateMapper;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserResponseDto createUser(UserCreationDto dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new BadRequestException("Email already registered");
        }
        User user = mapper.toEntity(dto);
        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(user);
        System.out.println("JWT token: " + token);
        return mapper.toDto(savedUser, token);
    }

    @Override
    public UserResponseDto loginUser(LoginRequestDto dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        String token = jwtService.generateToken(user);
        return mapper.toDto(user, token);
    }

    @Override
    public UserResponseDto getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return mapper.toDto(user, null);
    }

    @Override
    public UpdateUserRequestDto updateUser(UpdateUserRequestDto dto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        user.setFullName(dto.fullName());
        user.setMobileNumber(dto.mobileNumber());
        User updatedUser = userRepository.save(user);

        return updateMapper.toDto(updatedUser);
    }

}
