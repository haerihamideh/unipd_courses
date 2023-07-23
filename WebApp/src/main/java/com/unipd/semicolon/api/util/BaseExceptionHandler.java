package com.unipd.semicolon.api.util;

import com.unipd.semicolon.api.util.helper.ResponseHelper;
import com.unipd.semicolon.business.exception.*;
import com.unipd.semicolon.business.exception.IllegalArgumentException;
import com.unipd.semicolon.business.exception.IllegalStateException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.EOFException;
import java.sql.SQLException;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
        if (ex instanceof NullPointerException) {
            return ResponseHelper.response(
                    "ex.getCause()",
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
        return ResponseHelper.response(
                ex.getClass(),
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(UserNameOrPasswordNotExistsException.class)
    public ResponseEntity<Object> UserNameOrPasswordNotExitsException(
            UserNameOrPasswordNotExistsException e) {
        return ResponseHelper.response(
                "Username: " + e.getUsername() + "  " + "Password : " + e.getPassword(),
                e.getMsg(),
                e.getStatus()
        );
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Object> InvalidTokenException(
            InvalidTokenException e) {
        return ResponseHelper.response(
                e.getToken(),
                e.getMsg(),
                e.getStatus()
        );
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<Object> UserExistsException(
            UserExistsException e) {
        return ResponseHelper.response(
                e.getClass(),
                e.getMsg(),
                e.getStatus()
        );
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> SQLException(
            SQLException e) {
        return ResponseHelper.response(
                e.getClass(),
                e.getMessage(),
                HttpStatus.SERVICE_UNAVAILABLE
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> IllegalArgumentException(
            IllegalArgumentException e) {
        return ResponseHelper.response(
                e.getClass(),
                e.getMsg(),
                e.getStatus()
        );
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> IllegalStateException(
            IllegalStateException e) {
        return ResponseHelper.response(
                e.getClass(),
                e.getMsg(),
                e.getStatus()
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> EntityNotFoundException(
            EntityNotFoundException e) {
        return ResponseHelper.response(
                e.getClass(),
                e.getMsg(),
                e.getStatus()
        );
    }

    @ExceptionHandler(AccountAccessNotFound.class)
    public ResponseEntity<Object> AccountAccessNotFound(
            AccountAccessNotFound e) {
        return ResponseHelper.response(
                e.getClass(),
                e.getMsg(),
                e.getStatus()
        );
    }

    @ExceptionHandler(CreatePharmacyDataNotFound.class)
    public ResponseEntity<Object> CreatePharmacyDataNotFound(
            CreatePharmacyDataNotFound e) {
        return ResponseHelper.response(
                e.getClass(),
                e.getMsg(),
                e.getStatus()
        );
    }

    @ExceptionHandler(DrugExistsException.class)
    public ResponseEntity<Object> DrugExistsException(
            DrugExistsException e) {
        return ResponseHelper.response(
                e.getClass(),
                e.getMsg(),
                e.getStatus()
        );
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<Object> InvalidParameterException(
            InvalidParameterException e) {
        return ResponseHelper.response(
                e.getClass(),
                e.getMsg(),
                e.getStatus()
        );
    }

    @ExceptionHandler(PermissionException.class)
    public ResponseEntity<Object> PermissionException(
            PermissionException e) {
        return ResponseHelper.response(
                e.getData(),
                e.getMsg(),
                e.getStatus()
        );
    }

    @ExceptionHandler(PharmacyExistsException.class)
    public ResponseEntity<Object> PharmacyExistsException(
            PharmacyExistsException e) {
        return ResponseHelper.response(
                e.getClass(),
                e.getMsg(),
                e.getStatus()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> RuntimeException(
            RuntimeException e) {
        return ResponseHelper.response(
                e.getCause(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> NotFoundException(
            NotFoundException e) {
        return ResponseHelper.response(
                e.getClass(),
                e.getMessage(),
                HttpStatus.NOT_ACCEPTABLE
        );
    }

    @ExceptionHandler(ClassNotFoundException.class)
    public ResponseEntity<Object> ClassNotFoundException(
            ClassNotFoundException e) {
        return ResponseHelper.response(
                e.getClass(),
                e.getMessage(),
                HttpStatus.NOT_ACCEPTABLE
        );
    }


    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> HttpClientErrorException(
            HttpClientErrorException e) {
        return ResponseHelper.response(
                e.getClass(),
                e.getMessage(),
                (HttpStatus) e.getStatusCode()
        );
    }


}
