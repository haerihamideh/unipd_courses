package com.unipd.semicolon.business.exception;

import org.springframework.http.HttpStatus;

public class AccountAccessNotFound extends CustomException {

  public AccountAccessNotFound() {
    super("Account_access_not_found");
  }

  public AccountAccessNotFound(String updateLink) {
    super("Account_access_not_found", HttpStatus.BAD_REQUEST, updateLink);
  }
}
