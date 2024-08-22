package com.bkartisan.be.Dto;

import java.util.List;

public record ProductsForHomePageDTO(
        Integer length,
        List<ProductsItemForHomePage> products) {
}
