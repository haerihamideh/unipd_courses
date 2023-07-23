package com.unipd.semicolon.business.service;

import com.unipd.semicolon.business.exception.CustomException;
import com.unipd.semicolon.core.domain.AccountResponse;
import com.unipd.semicolon.core.entity.Login;
import com.unipd.semicolon.core.entity.User;

public interface AccountService {

    AccountResponse Login (
            String username,
            String password
    ) throws CustomException;

    Boolean LogOut(
            String token
    ) throws CustomException;


    Login save(
            String username,
            String password,
            User user
    );

    Login findByUserName(String username);


}
