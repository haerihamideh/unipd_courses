package com.unipd.semicolon.business.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends CustomException {

    private String token;

    public InvalidTokenException(String token) {
        this.setMsg("Invalid_Token_Exception");
        this.setStatus(HttpStatus.FORBIDDEN);
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
