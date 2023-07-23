package com.unipd.semicolon.business.exception;

import org.springframework.http.HttpStatus;

public class PharmacyExistsException extends CustomException {

  public PharmacyExistsException() {
    super("pharmacy_exists_before", HttpStatus.METHOD_NOT_ALLOWED);
  }
}
