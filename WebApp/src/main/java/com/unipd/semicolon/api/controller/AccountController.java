package com.unipd.semicolon.api.controller;

import com.unipd.semicolon.api.model.LoginRequest;
import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.exception.CustomException;
import com.unipd.semicolon.business.exception.InvalidTokenException;
import com.unipd.semicolon.business.exception.UserExistsException;
import com.unipd.semicolon.business.service.AccountService;
import com.unipd.semicolon.core.entity.enums.Country;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Operation(summary = "login", description = "will send all info of user and new token bane id and role.")
    public ResponseEntity login(
            @RequestParam(required = true) String username,
            @RequestParam(required = true) String password)
            throws CustomException {
        return ResponseHelper.response(accountService.Login(
                username,
                password));

    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @Operation(summary = "logout", description = "remove the token from login entity.")
    public ResponseEntity logout(@RequestHeader("Authorization") String token)
            throws CustomException {
        return ResponseHelper.response(accountService.LogOut(token));
    }


}
