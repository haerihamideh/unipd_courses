package com.unipd.semicolon.business.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends CustomException {
    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

}
