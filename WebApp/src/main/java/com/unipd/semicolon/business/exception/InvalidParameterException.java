package com.unipd.semicolon.business.exception;

import org.springframework.http.HttpStatus;

public class InvalidParameterException extends CustomException {

  public InvalidParameterException() {
    super("Invalid_Parameter_Exception", HttpStatus.BAD_REQUEST);
  }
}
