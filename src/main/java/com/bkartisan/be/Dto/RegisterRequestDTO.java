package com.bkartisan.be.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class RegisterRequestDTO {
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @NotBlank(message = "Email cannot be blank")
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