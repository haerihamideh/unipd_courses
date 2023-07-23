package com.unipd.semicolon.business.service;

public interface SecurityService {

    String createToken(String accountId, String role);

    boolean validateToken(String token);

    String getAccountId(String token);

    String getRoleFromToken(String token);
}
