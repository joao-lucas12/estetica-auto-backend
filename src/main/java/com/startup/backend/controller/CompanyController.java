package com.startup.backend.controller;

import com.startup.backend.dto.CompanyCreateRequest;
import com.startup.backend.dto.CompanyResponse;
import com.startup.backend.dto.CompanyUpdateRequest;
import com.startup.backend.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> create(@Valid @RequestBody CompanyCreateRequest request) {
        CompanyResponse response = companyService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> findById(@PathVariable Long id) {
        CompanyResponse response = companyService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> findAll() {
        List<CompanyResponse> response = companyService.findAll();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody CompanyUpdateRequest request) {
        CompanyResponse response = companyService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/toggle-active")
    public ResponseEntity<CompanyResponse> toggleActive(@PathVariable Long id) {
        CompanyResponse response = companyService.toggleActive(id);
        return ResponseEntity.ok(response);
    }
}