package com.unipd.semicolon.core.domain;

public class UserListExampleResponse {
    private String fullName;

    public UserListExampleResponse(String fullName) {
        this.fullName = fullName;
    }

    public UserListExampleResponse() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
