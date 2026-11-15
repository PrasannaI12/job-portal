package com.example.jobportal.service.impl;

import com.example.jobportal.dto.EmployerApplicationResponseDto;
import com.example.jobportal.dto.JobApplicationResponseDto;
import com.example.jobportal.dto.UpdateApplicationStatusDto;
import com.example.jobportal.entity.*;
import com.example.jobportal.exception.BadRequestException;
import com.example.jobportal.exception.ResourceConflictException;
import com.example.jobportal.exception.ResourceNotFoundException;
import com.example.jobportal.mapper.UserMapper;
import com.example.jobportal.repository.JobApplicationRepository;
import com.example.jobportal.repository.JobPostRepository;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.service.JobApplicationService;
import org.hibernate.sql.Update;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;
    private final UserMapper mapper;

    public JobApplicationServiceImpl(JobApplicationRepository applicationRepository,
                                     UserRepository userRepository,
                                     JobPostRepository jobPostRepository,
                                     UserMapper mapper) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.jobPostRepository = jobPostRepository;
        this.mapper = mapper;
    }

    @Override
    public JobApplicationResponseDto applyToJob(Long seekerId, Long jobId) {
        User seeker = userRepository.findById(seekerId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + seekerId));
        JobPost jobPost = jobPostRepository.findById(jobId)
                .orElseThrow(() -> new BadRequestException("Job post not found with id: " + jobId));
        if (applicationRepository.existsBySeekerIdAndJobPostId(seekerId, jobId)) {
            throw new ResourceConflictException("You have already applied for this job.");
        }
        JobApplication application = new JobApplication();
        application.setJobPost(jobPost);
        application.setSeeker(seeker);
        application.setStatus(ApplicationStatus.APPLIED);
        JobApplication savedApplication = applicationRepository.save(application);
        return mapper.toDto(savedApplication);
    }

    @Override
    public List<JobApplicationResponseDto> getAllApplications(Long seekerId) {
        User seeker = userRepository.findById(seekerId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + seekerId));
        return applicationRepository.findAllBySeekerId(seeker.getId())
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<EmployerApplicationResponseDto> getApplicationsForJob(Long employerId, Long jobId) {
        User employer = userRepository.findById(employerId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + employerId));
        JobPost jobPost = jobPostRepository.findById(jobId)
                .orElseThrow(() -> new BadRequestException("Job post not found with id: " + jobId));
        if (employer.getCompany() == null) {
            throw new ResourceNotFoundException("You must have a registered company to manage applications.");
        }
        if (!employer.getCompany().getId().equals(jobPost.getCompany().getId())) {
            throw new AccessDeniedException("You are not authorized to view applications for this job");
        }
        return applicationRepository.findAllByJobPostId(jobId)
                .stream()
                .map(mapper::toAppDto)
                .toList();
    }

    @Override
    public EmployerApplicationResponseDto getApplicationById(Long employerId, Long applicationId) {
        User employer = userRepository.findById(employerId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + employerId));
        JobApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id: " + applicationId));
        if (employer.getCompany() == null) {
            throw new ResourceNotFoundException("You must have a registered company to manage applications.");
        }
        if (!application.getJobPost().getCompany().getId().equals(employer.getCompany().getId())) {
            throw new AccessDeniedException("You are not authorized to view applications for this job");
        }
        return mapper.toAppDto(application);
    }

    @Override
    public EmployerApplicationResponseDto updateApplicationStatus(Long employerId,
                                                                  Long applicationId,
                                                                  UpdateApplicationStatusDto dto) {
        User employer = userRepository.findById(employerId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + employerId));
        if (employer.getCompany() == null) {
            throw new ResourceNotFoundException("You must have a registered company to manage applications.");
        }
        JobApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id: " + applicationId));
        Long employerCompanyId = employer.getCompany().getId();
        Long jobCompanyId = application.getJobPost().getCompany().getId();
        if (!employerCompanyId.equals(jobCompanyId)) {
            throw new AccessDeniedException("You are not authorized to update applications " +
                    "for another company's job");
        }
        application.setStatus(dto.status());
        JobApplication updatedApplication = applicationRepository.save(application);
        return mapper.toAppDto(updatedApplication);
    }
}
