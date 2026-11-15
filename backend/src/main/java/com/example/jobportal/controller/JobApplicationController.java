package com.example.jobportal.controller;

import com.example.jobportal.dto.EmployerApplicationResponseDto;
import com.example.jobportal.dto.JobApplicationResponseDto;
import com.example.jobportal.dto.UpdateApplicationStatusDto;
import com.example.jobportal.entity.ApplicationStatus;
import com.example.jobportal.entity.User;
import com.example.jobportal.service.JobApplicationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JobApplicationController {

    private final JobApplicationService applicationService;

    public JobApplicationController(JobApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/jobs/{jobId}/apply")
    @PreAuthorize("hasRole('SEEKER')")
    public ResponseEntity<JobApplicationResponseDto> applyToJob(@PathVariable Long jobId,
                                                                         @AuthenticationPrincipal User seeker) {
        JobApplicationResponseDto responseDto = applicationService.applyToJob(seeker.getId(), jobId);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/applications/my-applications")
    @PreAuthorize("hasRole('SEEKER')")
    public ResponseEntity<List<JobApplicationResponseDto>> getAllApplications(
            @AuthenticationPrincipal User seeker) {
        return ResponseEntity.ok(applicationService.getAllApplications(seeker.getId()));
    }

    @GetMapping("/jobs/{jobId}/applications")
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<List<EmployerApplicationResponseDto>> getApplicationsForJob(
            @PathVariable Long jobId, @AuthenticationPrincipal User employer) {
        return ResponseEntity.ok(applicationService.getApplicationsForJob(employer.getId(), jobId));
    }

    @GetMapping("/applications/{applicationId}")
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<EmployerApplicationResponseDto> getApplicationById(
            @AuthenticationPrincipal User employer, @PathVariable Long applicationId) {
        return ResponseEntity.ok(applicationService.getApplicationById(employer.getId(), applicationId));
    }

    @PatchMapping("/applications/{applicationId}/status")
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<EmployerApplicationResponseDto> updateApplicationStatus(
            @AuthenticationPrincipal User employer,
            @PathVariable Long applicationId,
            @Valid @RequestBody UpdateApplicationStatusDto dto) {
        EmployerApplicationResponseDto response =
                applicationService.updateApplicationStatus(employer.getId(), applicationId, dto);
        return ResponseEntity.ok(response);
    }
}
