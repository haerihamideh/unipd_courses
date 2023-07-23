package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.service.StringService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class StringServiceImp implements StringService {

    @Override
    public String encodeBase64(String input) {
        return new String(Base64.encodeBase64String(input.getBytes()));
    }

    @Override
    public String decodeBase64(String input) {
        return new String(Base64.decodeBase64(input));
    }

    /*@Override
    public String bCryptPasswordEncoder(String input) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(input);
        return encode;
    }

    @Override
    public Boolean verifyBCryptPassword(
            String rawInput,
            String hashInput) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(rawInput, hashInput);
    }*/
}
