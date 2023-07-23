package com.unipd.semicolon.core.domain;

import com.unipd.semicolon.core.entity.User;

import java.time.LocalDateTime;

public class AccountResponse {

    private String username;
    private String Token;
    private LocalDateTime lastLoginDate;

    private User user;

    public AccountResponse(
            String username,
            String token,
            LocalDateTime lastLoginDate,
            User user) {
        this.username = username;
        Token = token;
        this.lastLoginDate = lastLoginDate;
        this.user = user;
    }

    public AccountResponse() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
