package com.example.jobportal.service;

import com.example.jobportal.dto.CompanyCreationDto;
import com.example.jobportal.dto.CompanyResponseDto;

import java.util.List;

public interface CompanyService {

    CompanyResponseDto createCompany(Long userId, CompanyCreationDto dto);

    CompanyResponseDto getCompany(Long employerId);

    CompanyCreationDto updateCompany(Long employerId, CompanyCreationDto dto);

    List<CompanyResponseDto> getAllCompanies();

    CompanyResponseDto getCompanyById(Long companyId);
}
