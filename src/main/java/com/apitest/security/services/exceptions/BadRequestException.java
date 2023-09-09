package com.apitest.security.services.exceptions;

public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BadRequestException(String messagem) {
        super(messagem);
    }
}
