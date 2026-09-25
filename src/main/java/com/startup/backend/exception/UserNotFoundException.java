package com.startup.backend.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String detail) {
        super("Usuário não encontrado: " + detail);
    }
}