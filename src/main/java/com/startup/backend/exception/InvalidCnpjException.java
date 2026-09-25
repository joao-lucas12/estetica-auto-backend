package com.startup.backend.exception;

public class InvalidCnpjException extends RuntimeException {

    public InvalidCnpjException(String cnpj) {
        super("CNPJ inválido: " + cnpj);
    }
}