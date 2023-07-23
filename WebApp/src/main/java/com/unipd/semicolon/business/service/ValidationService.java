package com.unipd.semicolon.business.service;

import com.unipd.semicolon.business.exception.CustomException;
import com.unipd.semicolon.core.entity.Role;
import com.unipd.semicolon.core.entity.User;
import com.unipd.semicolon.core.entity.enums.Gender;
import com.unipd.semicolon.core.entity.enums.PaymentMethod;

import java.time.LocalDateTime;
import java.util.Date;

public interface ValidationService {
    Boolean validatePaymentMethod(PaymentMethod paymentMethod);

    Boolean validateImage(byte[] image, int maxSize);

    Boolean validateDate(Date date, Boolean futureCheck);

    Boolean validateEmail(String email);

    Boolean validateTelephoneNumber(String telephoneNumber);

    Boolean validatePrice(float price);

    Boolean validateBirthDate(LocalDateTime dateTime);

    Boolean validateGender(Gender gender);
}
