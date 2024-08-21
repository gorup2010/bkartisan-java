package com.bkartisan.be.Dto;

public class RegisterRequestDTO {
    private String username;
    private String password;
    private String email;

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public String email() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}