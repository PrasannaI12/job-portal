package com.example.jobportal.controller;

import com.example.jobportal.dto.JobPostCreationDto;
import com.example.jobportal.dto.JobPostResponseDto;
import com.example.jobportal.entity.User;
import com.example.jobportal.service.JobPostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobPostController {

    private final JobPostService jobPostService;

    public JobPostController(JobPostService jobPostService) {
        this.jobPostService = jobPostService;
    }

    @PostMapping
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<JobPostResponseDto> createJob(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody JobPostCreationDto dto) {
        return new ResponseEntity<>(jobPostService.createJob(user.getId(), dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<JobPostResponseDto>> getAllJobs() {
        return ResponseEntity.ok(jobPostService.getAllJobs());
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobPostResponseDto> getJobById(@PathVariable Long jobId) {
        return ResponseEntity.ok(jobPostService.getJobById(jobId));
    }

    @PutMapping("/{jobId}")
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<JobPostCreationDto> updateJob(@AuthenticationPrincipal User employer,
                                                        @PathVariable Long jobId,
                                                        @Valid @RequestBody JobPostCreationDto dto) {
        return ResponseEntity.ok(jobPostService.updateJob(employer.getId(), jobId, dto));
    }

    @DeleteMapping("/{jobId}")
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<String> deleteJob(@AuthenticationPrincipal User employer,
                                            @PathVariable Long jobId) {
        return ResponseEntity.ok(jobPostService.deleteJob(employer.getId(), jobId));
    }

    @GetMapping("/my-jobs")
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<List<JobPostResponseDto>> getMyJobs(@AuthenticationPrincipal User employer) {
        return ResponseEntity.ok(jobPostService.getMyJobs(employer.getId()));
    }
}
