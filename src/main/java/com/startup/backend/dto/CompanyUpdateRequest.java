package com.startup.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

public record CompanyUpdateRequest(

        String name,

        String phone,

        @Email(message = "E-mail em formato inválido")
        String email,

        String address,

        String city,

        String state,

        @Min(value = 1, message = "A capacidade mínima é 1")
        Integer capacity
) {
}