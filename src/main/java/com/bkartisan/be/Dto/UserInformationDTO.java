package com.bkartisan.be.Dto;

import java.time.LocalDateTime;

public record UserInformationDTO(
        String username,
        String name,
        String email,
        String address,
        String numPhone,
        String avatar,
        String role,
        String nation,
        Character gender,
        LocalDateTime lockUntil,
        LocalDateTime createdAt) {
}
