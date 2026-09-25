package com.startup.backend.exception;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(Long id) {
        super("Empresa não encontrada: id " + id);
    }
}