package com.bkartisan.be.Dto;

import com.bkartisan.be.Constant.ProductStatus;


public record ProductFilterForSellerPageDTO(
        String searchTerm,
        ProductStatus status,
        Boolean isSoldOut,
        Integer page,
        Integer offset) {
}
