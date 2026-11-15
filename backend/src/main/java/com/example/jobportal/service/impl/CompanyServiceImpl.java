package com.example.jobportal.service.impl;

import com.example.jobportal.dto.CompanyCreationDto;
import com.example.jobportal.dto.CompanyResponseDto;
import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.Role;
import com.example.jobportal.entity.User;
import com.example.jobportal.exception.BadRequestException;
import com.example.jobportal.exception.ResourceNotFoundException;
import com.example.jobportal.mapper.UserMapper;
import com.example.jobportal.mapper.UserUpdateMapper;
import com.example.jobportal.repository.CompanyRepository;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final UserMapper mapper;
    private final UserRepository userRepository;
    private final UserUpdateMapper updateMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, UserMapper mapper,
                              UserRepository userRepository, UserUpdateMapper updateMapper) {
        this.companyRepository = companyRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.updateMapper = updateMapper;
    }

    @Override
    public CompanyResponseDto createCompany(Long userId, CompanyCreationDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        if (user.getCompany() != null) {
            throw new BadRequestException("You have already registered a company profile");
        }
        Company company = mapper.toEntity(dto);
        Company savedCompany = companyRepository.save(company);
        //Associate the saved company with the user and persist the updated user record
        user.setCompany(savedCompany);
        userRepository.save(user);
        return mapper.toDto(savedCompany);
    }

    @Override
    public CompanyResponseDto getCompany(Long employerId) {
        User employer = userRepository.findById(employerId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + employerId));
        Long companyId = employer.getCompany().getId();
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + companyId));
        return mapper.toDto(company);
    }

    @Override
    public CompanyCreationDto updateCompany(Long employerId, CompanyCreationDto dto) {
        User employer = userRepository.findById(employerId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + employerId));
        Company company = employer.getCompany();
        if (company == null) {
            throw new ResourceNotFoundException("You have not registered a company  yet");
        }
        company.setCompanyName(dto.companyName());
        company.setCompanyOverview(dto.companyOverview());
        company.setHeadquarters(dto.headquarters());
        company.setWebsite(dto.website());
        company.setEmployees(dto.employees());
        company.setLocation(dto.location());
        Company updatedCompany = companyRepository.save(company);
        return updateMapper.toDto(updatedCompany);
    }

    @Override
    public List<CompanyResponseDto> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public CompanyResponseDto getCompanyById(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + companyId));
        return mapper.toDto(company);
    }
}
