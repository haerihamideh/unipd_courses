package com.unipd.semicolon.core.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "token")
    private String Token;
    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;
    @Column(name = "activation_date")
    private LocalDateTime activationDate;
    @Column(name = "termination_date")
    private LocalDateTime terminationDate;

    @OneToOne
    private User user;

    public Login() {

    }

    public Login(
            String username,
            String password,
            String token,
            LocalDateTime lastLoginDate,
            LocalDateTime activationDate,
            LocalDateTime terminationDate,
            User user) {
        this.username = username;
        this.password = password;
        this.Token = token;
        this.lastLoginDate = lastLoginDate;
        this.activationDate = activationDate;
        this.terminationDate = terminationDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public LocalDateTime getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDateTime activationDate) {
        this.activationDate = activationDate;
    }

    public LocalDateTime getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(LocalDateTime terminationDate) {
        this.terminationDate = terminationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}