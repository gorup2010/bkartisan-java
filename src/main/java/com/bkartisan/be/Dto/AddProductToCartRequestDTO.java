package com.bkartisan.be.Dto;

public record AddProductToCartRequestDTO(
    Integer productId,
    Integer quantity
) {
}
