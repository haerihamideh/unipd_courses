package com.unipd.semicolon.core.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.unipd.semicolon.core.entity.enums.Gender;
import jakarta.persistence.*;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

@Entity
@Table(name = "_User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;
    @OneToOne
    private Role role;

    @Column(name = "email")
    private String email;

    @Column(name = "account_status")
    private String accountStatus;

    @Column(name = "string_profile_picture", columnDefinition = "TEXT")
    private String profilePicture;

    @JsonBackReference
    @ManyToOne
    private Pharmacy pharmacy;

    public User() {
    }

    public User(
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
            String profilePicture,
            Pharmacy pharmacy) {
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
        this.profilePicture = profilePicture;
        this.pharmacy = pharmacy;
    }

    public User(
            String name,
            String lastName,
            Gender gender,
            LocalDateTime birthDate,
            String phoneNumber,
            String address,
            Role role,
            String email,
            String accountStatus,
            String profilePicture,
            Pharmacy pharmacy) {
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.email = email;
        this.accountStatus = accountStatus;
        this.profilePicture = profilePicture;
        this.pharmacy = pharmacy;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Pharmacy getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}
