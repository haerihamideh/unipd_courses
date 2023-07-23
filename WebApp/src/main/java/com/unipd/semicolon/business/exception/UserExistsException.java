package com.unipd.semicolon.business.exception;

import org.springframework.http.HttpStatus;

public class UserExistsException extends CustomException {

  public UserExistsException() {
    super("user_exists", HttpStatus.NOT_FOUND);
  }
}
