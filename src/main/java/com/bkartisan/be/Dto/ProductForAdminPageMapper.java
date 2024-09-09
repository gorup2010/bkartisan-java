package com.bkartisan.be.Dto;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.bkartisan.be.Entity.Product;

@Service
public class ProductForAdminPageMapper implements Function<Product, ProductForAdminPageDTO> {

    @Override
    public ProductForAdminPageDTO apply(Product t) {
        return new ProductForAdminPageDTO(t.getProductId(), t.getCoverImage(), t.getName(), t.getSeller().getName(), t.getPrice(),
                t.getApprovedAt());
    }
}
