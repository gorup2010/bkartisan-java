package com.bkartisan.be.Dto;

import java.time.LocalDateTime;

public record ProductForAdminPageDTO(
        Integer productId,
        String coverImage,
        String name,
        String seller,
        Integer price,
        LocalDateTime approvedAt) {
}
