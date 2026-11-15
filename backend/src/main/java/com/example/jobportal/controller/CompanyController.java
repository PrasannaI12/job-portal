package com.example.jobportal.controller;

import com.example.jobportal.dto.CompanyCreationDto;
import com.example.jobportal.dto.CompanyResponseDto;
import com.example.jobportal.entity.User;
import com.example.jobportal.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<CompanyResponseDto> createCompany(
            @AuthenticationPrincipal User employer,
            @Valid @RequestBody CompanyCreationDto dto) {
        return new ResponseEntity<>(companyService.createCompany(employer.getId(), dto), HttpStatus.CREATED);
    }

    @GetMapping("/my-company")
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<CompanyResponseDto> getCurrentCompany(@AuthenticationPrincipal User employer) {
        return ResponseEntity.ok(companyService.getCompany(employer.getId()));
    }

    @PutMapping("/my-company")
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<CompanyCreationDto> updateCurrentCompany(@AuthenticationPrincipal User employer,
                                                  @Valid @RequestBody CompanyCreationDto dto) {
        return ResponseEntity.ok(companyService.updateCompany(employer.getId(), dto));
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponseDto>> getAllCompanies() {
        List<CompanyResponseDto> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }
}
