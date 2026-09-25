package com.startup.backend.repository;

import com.startup.backend.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByCnpj(String cnpj);
}