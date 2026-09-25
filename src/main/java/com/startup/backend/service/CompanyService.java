package com.startup.backend.service;

import com.startup.backend.dto.CompanyCreateRequest;
import com.startup.backend.dto.CompanyResponse;
import com.startup.backend.dto.CompanyUpdateRequest;
import com.startup.backend.entity.Company;
import com.startup.backend.exception.CnpjAlreadyExistsException;
import com.startup.backend.exception.CompanyNotFoundException;
import com.startup.backend.exception.InvalidCnpjException;
import com.startup.backend.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyResponse create(CompanyCreateRequest request) {

        String cleanCnpj = request.cnpj().replaceAll("\\D", "");

        if (!isCnpjValid(cleanCnpj)) {
            throw new InvalidCnpjException(request.cnpj());
        }

        if (companyRepository.existsByCnpj(cleanCnpj)) {
            throw new CnpjAlreadyExistsException(request.cnpj());
        }

        Company company = new Company();
        company.setName(request.name());
        company.setCnpj(cleanCnpj);
        company.setPhone(request.phone());
        company.setEmail(request.email());
        company.setAddress(request.address());
        company.setCity(request.city());
        company.setState(request.state());
        company.setCapacity(request.capacity());
        company.setActive(true);

        Company saved = companyRepository.save(company);

        return toResponse(saved);
    }

    public CompanyResponse findById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
        return toResponse(company);
    }

    public List<CompanyResponse> findAll() {
        return companyRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public CompanyResponse update(Long id, CompanyUpdateRequest request) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));

        if (request.name() != null) {
            company.setName(request.name());
        }
        if (request.phone() != null) {
            company.setPhone(request.phone());
        }
        if (request.email() != null) {
            company.setEmail(request.email());
        }
        if (request.address() != null) {
            company.setAddress(request.address());
        }
        if (request.city() != null) {
            company.setCity(request.city());
        }
        if (request.state() != null) {
            company.setState(request.state());
        }
        if (request.capacity() != null) {
            company.setCapacity(request.capacity());
        }

        Company updated = companyRepository.save(company);

        return toResponse(updated);
    }

    public CompanyResponse toggleActive(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));

        company.setActive(!company.getActive());

        Company updated = companyRepository.save(company);

        return toResponse(updated);
    }

    private boolean isCnpjValid(String cnpj) {
        if (cnpj.length() != 14) {
            return false;
        }

        if (cnpj.chars().distinct().count() == 1) {
            return false;
        }

        int firstCheckDigit = calculateCheckDigit(cnpj.substring(0, 12), new int[]{5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2});
        int secondCheckDigit = calculateCheckDigit(cnpj.substring(0, 12) + firstCheckDigit, new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2});

        String expectedCheckDigits = "" + firstCheckDigit + secondCheckDigit;
        String actualCheckDigits = cnpj.substring(12);

        return expectedCheckDigits.equals(actualCheckDigits);
    }

    private int calculateCheckDigit(String base, int[] weights) {
        int sum = 0;
        for (int i = 0; i < base.length(); i++) {
            sum += Character.getNumericValue(base.charAt(i)) * weights[i];
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }

    private CompanyResponse toResponse(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getCnpj(),
                company.getPhone(),
                company.getEmail(),
                company.getAddress(),
                company.getCity(),
                company.getState(),
                company.getCapacity(),
                company.getActive(),
                company.getCreatedAt()
        );
    }
}