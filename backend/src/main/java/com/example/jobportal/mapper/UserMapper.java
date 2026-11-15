package com.example.jobportal.mapper;

import com.example.jobportal.dto.*;
import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.JobApplication;
import com.example.jobportal.entity.JobPost;
import com.example.jobportal.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    //Entity -> Response DTO (for returning to UI)
    public UserResponseDto toDto(User user, String token) {
        return new UserResponseDto(
                token,
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getMobileNumber(),
                user.getRole()
        );
    }

    //Request DTO -> Entity (for saving to DB)
    public User toEntity(UserCreationDto dto) {
        User user = new User();
        user.setFullName(dto.fullName());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        System.out.println("Bcrypt password: " + user.getPassword());
        user.setMobileNumber(dto.mobileNumber());
        user.setRole(dto.role());
        return user;
    }

    //Entity -> Response DTO (for returning to UI)
    public CompanyResponseDto toDto(Company company) {
        return new CompanyResponseDto(
                company.getId(),
                company.getCompanyName(),
                company.getCompanyOverview(),
                company.getHeadquarters(),
                company.getWebsite(),
                company.getEmployees(),
                company.getLocation()
        );
    }

    //Request DTO -> Entity (for saving to DB)
    public Company toEntity(CompanyCreationDto dto) {
        Company company = new Company();
        company.setCompanyName(dto.companyName());
        company.setCompanyOverview(dto.companyOverview());
        company.setHeadquarters(dto.headquarters());
        company.setWebsite(dto.website());
        company.setEmployees(dto.employees());
        company.setLocation(dto.location());
        return company;
    }

    public JobPostResponseDto toDto(JobPost jobPost) {
        return new JobPostResponseDto(
                jobPost.getId(),
                jobPost.getTitle(),
                jobPost.getDescription(),
                jobPost.getRequiredSkills(),
                jobPost.getEmploymentType(),
                jobPost.getEducation(),
                jobPost.getSalary(),
                jobPost.getLocation()
        );
    }

    public JobPost toEntity(JobPostCreationDto dto) {
        JobPost jobPost = new JobPost();
        jobPost.setTitle(dto.title());
        jobPost.setDescription(dto.description());
        jobPost.setRequiredSkills(dto.requiredSkills());
        jobPost.setEmploymentType(dto.employmentType());
        jobPost.setEducation(dto.education());
        jobPost.setSalary(dto.salary());
        jobPost.setLocation(dto.location());
        return jobPost;
    }

    public JobApplicationResponseDto toDto(JobApplication jobApplication) {
        return new JobApplicationResponseDto(
                jobApplication.getId(),
                jobApplication.getJobPost().getId(),
                jobApplication.getJobPost().getTitle(),
                jobApplication.getJobPost().getCompany().getCompanyName(),
                jobApplication.getStatus(),
                jobApplication.getAppliedAt()
        );
    }

    public EmployerApplicationResponseDto toAppDto(JobApplication jobApplication) {
        return new EmployerApplicationResponseDto(
                jobApplication.getId(),
                jobApplication.getJobPost().getId(),
                jobApplication.getJobPost().getTitle(),
                jobApplication.getSeeker().getId(),
                jobApplication.getSeeker().getFullName(),
                jobApplication.getSeeker().getEmail(),
                jobApplication.getSeeker().getMobileNumber(),
                jobApplication.getStatus(),
                jobApplication.getAppliedAt()
        );
    }
}
