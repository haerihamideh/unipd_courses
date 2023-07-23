package com.unipd.semicolon.business.exception;

import org.springframework.http.HttpStatus;

public class PermissionException extends CustomException {

    public PermissionException(String token) {
        super("permission denied", HttpStatus.FORBIDDEN);
        this.setData("Token :" + token);
    }
}