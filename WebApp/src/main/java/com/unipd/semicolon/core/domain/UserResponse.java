package com.unipd.semicolon.core.domain;

import com.unipd.semicolon.core.entity.Pharmacy;
import com.unipd.semicolon.core.entity.Role;
import com.unipd.semicolon.core.entity.enums.Gender;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

public class UserResponse {

    private Long id;
    private String name;
    private String lastName;
    private Gender gender;
    private LocalDateTime birthDate;
    private String phoneNumber;
    private String address;
    private Role role;
    private String email;
    private String accountStatus;

    private String profileImage;


    public UserResponse() {
    }

    public UserResponse(
            Long id,
            String name,
            String lastName,
            Gender gender,
            LocalDateTime birthDate,
            String phoneNumber,
            String address,
            Role role,
            String email,
            String accountStatus,
            String profileImage) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.email = email;
        this.accountStatus = accountStatus;
        this.profileImage = profileImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public LocalDateTime getBirthday() {
        return birthDate;
    }

    public void setBirthday(Date birthday) {
        this.birthDate = birthDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}