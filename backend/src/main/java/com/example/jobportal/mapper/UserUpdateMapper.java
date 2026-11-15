package com.example.jobportal.mapper;

import com.example.jobportal.dto.CompanyCreationDto;
import com.example.jobportal.dto.JobPostCreationDto;
import com.example.jobportal.dto.UpdateUserRequestDto;
import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.JobPost;
import com.example.jobportal.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateMapper {
    public UpdateUserRequestDto toDto(User user) {
        return new UpdateUserRequestDto(
                user.getFullName(),
                user.getMobileNumber()
        );
    }

    public CompanyCreationDto toDto(Company company) {
        return new CompanyCreationDto(
                company.getCompanyName(),
                company.getCompanyOverview(),
                company.getHeadquarters(),
                company.getWebsite(),
                company.getEmployees(),
                company.getLocation()
        );
    }

    public JobPostCreationDto toDto(JobPost jobPost) {
        return new JobPostCreationDto(
                jobPost.getTitle(),
                jobPost.getDescription(),
                jobPost.getRequiredSkills(),
                jobPost.getEmploymentType(),
                jobPost.getEducation(),
                jobPost.getSalary(),
                jobPost.getLocation()
        );
    }
}
