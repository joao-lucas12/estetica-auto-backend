package com.startup.backend.exception;

public class CnpjAlreadyExistsException extends RuntimeException {

    public CnpjAlreadyExistsException(String cnpj) {
        super("Já existe uma empresa cadastrada com o CNPJ: " + cnpj);
    }
}