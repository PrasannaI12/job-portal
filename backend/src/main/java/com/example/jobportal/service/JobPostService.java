package com.example.jobportal.service;

import com.example.jobportal.dto.JobPostCreationDto;
import com.example.jobportal.dto.JobPostResponseDto;

import java.util.List;

public interface JobPostService {

    JobPostResponseDto createJob(Long userId, JobPostCreationDto dto);

    List<JobPostResponseDto> getAllJobs();

    JobPostResponseDto getJobById(Long jobId);

    JobPostCreationDto updateJob(Long userId, Long jobId, JobPostCreationDto dto);

    String deleteJob(Long userId, Long jobId);

    List<JobPostResponseDto> getMyJobs(Long userId);
}
