package com.unipd.semicolon.business.exception;

import org.springframework.http.HttpStatus;

public class IllegalStateException extends CustomException {

    public IllegalStateException() {
    }

    public IllegalStateException(String message) {
        this.setMsg(message);
        this.setStatus(HttpStatus.FAILED_DEPENDENCY);
    }

}
