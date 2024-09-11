package com.bkartisan.be.Dto;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.bkartisan.be.Entity.Product;

@Service
public class ProductForAdminPageMapper implements Function<Product, ProductsForAdminPageDTO> {

    @Override
    public ProductsForAdminPageDTO apply(Product t) {
        return new ProductsForAdminPageDTO(t.getProductId(), t.getCoverImage(), t.getName(), t.getSeller().getName(), t.getPrice(),
                t.getApprovedAt());
    }
}
