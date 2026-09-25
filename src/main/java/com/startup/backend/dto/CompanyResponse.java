package com.startup.backend.dto;

import java.time.LocalDateTime;

public record CompanyResponse(
        Long id,
        String name,
        String cnpj,
        String phone,
        String email,
        String address,
        String city,
        String state,
        Integer capacity,
        Boolean active,
        LocalDateTime createdAt
) {
}