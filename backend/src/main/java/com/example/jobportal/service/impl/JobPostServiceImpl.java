package com.example.jobportal.service.impl;

import com.example.jobportal.dto.JobPostCreationDto;
import com.example.jobportal.dto.JobPostResponseDto;
import com.example.jobportal.entity.JobPost;
import com.example.jobportal.entity.User;
import com.example.jobportal.exception.BadRequestException;
import com.example.jobportal.exception.ResourceNotFoundException;
import com.example.jobportal.mapper.UserMapper;
import com.example.jobportal.mapper.UserUpdateMapper;
import com.example.jobportal.repository.JobPostRepository;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.service.JobPostService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPostServiceImpl implements JobPostService {

    private final JobPostRepository jobPostRepository;
    private final UserMapper mapper;
    private final UserRepository userRepository;
    private final UserUpdateMapper updateMapper;

    public JobPostServiceImpl(JobPostRepository jobPostRepository, UserMapper mapper,
                              UserRepository userRepository, UserUpdateMapper updateMapper) {
        this.jobPostRepository = jobPostRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.updateMapper = updateMapper;
    }

    @Override
    public JobPostResponseDto createJob(Long userId, JobPostCreationDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        if (user.getCompany() == null) {
            throw new BadRequestException("You must register a company profile before posting a job");
        }
        JobPost jobPost = mapper.toEntity(dto);
        //Associate the employer's company with this job post
        jobPost.setCompany(user.getCompany());
        JobPost savedJobPost = jobPostRepository.save(jobPost);
        return mapper.toDto(savedJobPost);
    }

    @Override
    public List<JobPostResponseDto> getAllJobs(String keyword) {
        List<JobPost> jobs;
        if (keyword != null && !keyword.isBlank()) {
            jobs = jobPostRepository.searchByKeyword(keyword.trim());
        } else {
            jobs = jobPostRepository.findAll();
        }
        return jobs.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public JobPostResponseDto getJobById(Long jobId) {
        JobPost jobPost = jobPostRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job post not found with id: " + jobId));
        return mapper.toDto(jobPost);
    }

    @Override
    public JobPostCreationDto updateJob(Long userId, Long jobId, JobPostCreationDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        JobPost jobPost = jobPostRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job post not found with id: " + jobId));
        if (user.getCompany() == null) {
            throw new ResourceNotFoundException("You must register a company profile before updating the job");
        }
        if (!user.getCompany().getId().equals(jobPost.getCompany().getId())) {
            throw new AccessDeniedException("You are not authorized to update this job posting.");
        }
        jobPost.setTitle(dto.title());
        jobPost.setDescription(dto.description());
        jobPost.setRequiredSkills(dto.requiredSkills());
        jobPost.setEmploymentType(dto.employmentType());
        jobPost.setEducation(dto.education());
        jobPost.setSalary(dto.salary());
        jobPost.setLocation(dto.location());
        JobPost updatedJobPost = jobPostRepository.save(jobPost);
        return updateMapper.toDto(updatedJobPost);
    }

    @Override
    public String deleteJob(Long userId, Long jobId) {
        User employer = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        JobPost jobPost = jobPostRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job post not found with id: " + jobId));
        if (employer.getCompany() == null) {
            throw new ResourceNotFoundException("You must register a company profile before deleting the job");
        }
        if (!jobPost.getCompany().getId().equals(employer.getCompany().getId())) {
            throw new AccessDeniedException("You are not authorized to delete this job posting.");
        }
        jobPostRepository.deleteById(jobId);
        return "Job post with id: " + jobId + " deleted successfully";
    }

    @Override
    public List<JobPostResponseDto> getMyJobs(Long userId) {
        User employer = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        if (employer.getCompany() == null) {
            throw new ResourceNotFoundException("You have not created a company profile yet");
        }
        Long companyId = employer.getCompany().getId();
        return jobPostRepository.findAllByCompanyId(companyId)
                .stream().
                map(mapper::toDto).
                toList();
    }
}