package com.bkartisan.be.Dto;

public record ProductFilterForAdminPageDTO(
        String product,
        String seller,
        String collab,
        Integer startPrice,
        Integer endPrice,
        String orders,
        Integer offset,
        Integer page) {
}
