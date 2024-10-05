package com.bkartisan.be.Dto;

public record CreatePaymentRequestDTO(
    String name,
    String numPhone,
    String nation,
    String address
) {
}
