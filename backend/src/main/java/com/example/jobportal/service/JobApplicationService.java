package com.example.jobportal.service;

import com.example.jobportal.dto.EmployerApplicationResponseDto;
import com.example.jobportal.dto.JobApplicationResponseDto;
import com.example.jobportal.dto.UpdateApplicationStatusDto;
import com.example.jobportal.entity.ApplicationStatus;

import java.util.List;

public interface JobApplicationService {

    JobApplicationResponseDto applyToJob(Long seekerId, Long jobId);

    List<JobApplicationResponseDto> getAllApplications(Long seekerId);

    List<EmployerApplicationResponseDto> getApplicationsForJob(Long employerId, Long jobId);

    EmployerApplicationResponseDto getApplicationById(Long userId, Long applicationId);

    EmployerApplicationResponseDto updateApplicationStatus(Long employerId,
                                                           Long applicationId,
                                                           UpdateApplicationStatusDto request);
}
