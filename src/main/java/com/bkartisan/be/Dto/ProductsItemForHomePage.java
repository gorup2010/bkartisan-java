package com.bkartisan.be.Dto;

public record ProductsItemForHomePage(
        Integer productId,
        Integer price,
        String name,
        String seller,
        String coverImage,
        Integer numberOfStar,
        Integer numberOfRating,
        Integer discount) {
}
