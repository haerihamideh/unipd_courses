package com.unipd.semicolon.business.exception;

import org.springframework.http.HttpStatus;

public class DrugExistsException extends CustomException {

    public DrugExistsException() {
        super("drug_exists_before", HttpStatus.METHOD_NOT_ALLOWED);
    }
}
