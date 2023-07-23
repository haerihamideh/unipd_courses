package com.unipd.semicolon.business.exception;

import org.springframework.http.HttpStatus;

public class IllegalArgumentException extends CustomException {

    public IllegalArgumentException() {
    }

    public IllegalArgumentException(String message) {
        this.setMsg(message);
        this.setStatus(HttpStatus.BAD_REQUEST);
    }

}
