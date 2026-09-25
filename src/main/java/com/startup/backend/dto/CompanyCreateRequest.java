package com.startup.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CompanyCreateRequest(

        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "O CNPJ é obrigatório")
        String cnpj,

        @NotBlank(message = "O telefone é obrigatório")
        String phone,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail em formato inválido")
        String email,

        String address,

        String city,

        String state,

        @NotNull(message = "A capacidade é obrigatória")
        @Min(value = 1, message = "A capacidade mínima é 1")
        Integer capacity
) {
}