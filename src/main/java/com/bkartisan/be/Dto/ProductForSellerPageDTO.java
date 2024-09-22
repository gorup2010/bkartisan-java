package com.bkartisan.be.Dto;

import com.bkartisan.be.Constant.ProductStatus;

public record ProductForSellerPageDTO(
        Integer productId,
        Integer price,
        String name,
        String seller,
        String coverImage,
        Integer quantity,
        ProductStatus status) {
}
