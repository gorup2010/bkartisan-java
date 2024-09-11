package com.bkartisan.be.Dto;

import java.time.LocalDateTime;

public record ProductsForAdminPageDTO(
        Integer productId,
        String coverImage,
        String name,
        String seller,
        Integer price,
        LocalDateTime approvedAt) {
}
