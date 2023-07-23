package com.unipd.semicolon.business.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException {

  public NotFoundException() {
    super("not_found", HttpStatus.NOT_FOUND);
    this.setData("not_found");
  }
}
