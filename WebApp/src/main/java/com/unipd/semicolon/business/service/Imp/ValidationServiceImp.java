package com.unipd.semicolon.business.service.Imp;

import com.unipd.semicolon.business.exception.CustomException;
import com.unipd.semicolon.business.service.LogSystem;
import com.unipd.semicolon.business.service.ValidationService;
import com.unipd.semicolon.business.exception.IllegalArgumentException;
import com.unipd.semicolon.core.entity.enums.Gender;
import com.unipd.semicolon.core.entity.enums.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ValidationServiceImp implements ValidationService {

    @Autowired
    private LogSystem logSystem;

    // validate payment method
    @Override
    public Boolean validatePaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Payment method cannot be null");
        }

        PaymentMethod[] allowedMethods = {PaymentMethod.CASH, PaymentMethod.CREDIT_CARD,
                PaymentMethod.DEBIT_CARD, PaymentMethod.PAYPAL};
        boolean isValid = false;

        for (PaymentMethod allowedMethod : allowedMethods) {
            if (allowedMethod == paymentMethod) {
                isValid = true;
                break;
            }
        }

        if (!isValid) {
            logSystem.logUtil(
                    "Invalid payment method"
            );
            throw new IllegalArgumentException("Invalid payment method");
        }
        return true;
    }

    // validate size of an input image
    @Override
    public Boolean validateImage(byte[] image, int maxSize) {
        if (image != null) {
            if (image.length > maxSize) {
                logSystem.logUtil(
                        "Image size exceeds maximum size of " + (float) maxSize / (1024 * 1024) + " MB"
                );
                throw new IllegalArgumentException(
                        "Image size exceeds maximum size of " + (float) maxSize / (1024 * 1024) + " MB");
            }
            return true;
        }
        return true;
    }

    @Override
    public Boolean validateDate(Date date, Boolean futureCheck) {
        if (date == null) {
            logSystem.logUtil(
                    "Date cannot be null"
            );
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (futureCheck) {
            if (date.after(new Date())) {
                logSystem.logUtil(
                        "Date cannot be in the future."
                );
                throw new IllegalArgumentException("Date cannot be in the future.");
            }
        }
        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        cal.setTime(date);

        if (!cal.getTime().equals(date)) {
            logSystem.logUtil(
                    "Invalid date"
            );
            throw new IllegalArgumentException("Invalid date");
        }
        return true;
    }

    @Override
    public Boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            logSystem.logUtil(
                    "Email cannot be null or empty"
            );
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailRegex)) {
            logSystem.logUtil(
                    "Invalid email format"
            );
            throw new IllegalArgumentException("Invalid email format");
        }
        return true;
    }

    @Override
    public Boolean validateTelephoneNumber(String telephoneNumber) {
        if (telephoneNumber == null || telephoneNumber.isEmpty()) {
            logSystem.logUtil(
                    "Telephone number cannot be null or empty"
            );
            throw new IllegalArgumentException("Telephone number cannot be null or empty");
        }

        String digitsRegex = "\\d+";
        if (!telephoneNumber.matches(digitsRegex)) {
            logSystem.logUtil(
                    "Telephone number should contain only digits"
            );
            throw new IllegalArgumentException("Telephone number should contain only digits");
        }

        int minLength = 7; // minimum length of a telephone number
        int maxLength = 20; // maximum length of a telephone number
        if (telephoneNumber.length() < minLength || telephoneNumber.length() > maxLength) {
            logSystem.logUtil(
                    "Telephone number should be between 7 and 20 digits long"
            );
            throw new IllegalArgumentException("Telephone number should be between 7 and 20 digits long");
        }
        return true;
    }

    @Override
    public Boolean validatePrice(float price) {
        if (price <= 0) {
            logSystem.logUtil(
                    "Price should be greater than zero"
            );
            throw new IllegalArgumentException("Price should be greater than zero");
        }
        return true;

    }

    @Override
    public Boolean validateBirthDate(LocalDateTime birthDate) {
        if (birthDate == null) {
            logSystem.logUtil(
                    "Date cannot be null."
            );
            // Birthdate is null
            throw new IllegalArgumentException("Date cannot be null.");
        }

        LocalDateTime now = LocalDateTime.now();
        if (birthDate.isAfter(now)) {
            logSystem.logUtil(
                    "Date cannot be in the future."
            );
            // Birthdate is in the future
            throw new IllegalArgumentException("Date cannot be in the future.");
        }

        // Birth date is valid
        return true;
    }

    @Override
    public Boolean validateGender(Gender gender) {
        List<Gender> allowedGenders = Arrays.asList(
                Gender.FEMALE,
                Gender.MALE,
                Gender.UNISEX
        );

        if (gender == null || !allowedGenders.contains(gender)) {
            logSystem.logUtil(
                    "Invalid gender"
            );
            throw new CustomException("Invalid gender");
        }
        return true;
    }

}
