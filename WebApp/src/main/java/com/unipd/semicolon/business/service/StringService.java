package com.unipd.semicolon.business.service;

public interface StringService {

    String encodeBase64(String input);

    String decodeBase64(String input);

  /*  String bCryptPasswordEncoder(String input);

    Boolean verifyBCryptPassword(
            String rawInput,
            String hashInput);*/

}
