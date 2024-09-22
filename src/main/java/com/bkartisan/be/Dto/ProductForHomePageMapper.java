package com.bkartisan.be.Dto;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.bkartisan.be.Entity.Product;

@Service
public class ProductForHomePageMapper implements Function<Product, ProductForHomePageDTO> {
    @Override
    public ProductForHomePageDTO apply(Product product) {
        return new ProductForHomePageDTO(product.getProductId(), product.getPrice(), product.getName(),
                product.getSeller().getName(), product.getCoverImage(), product.getNumberOfStar(), product.getNumberOfRating(),
                product.getDiscount());
    }
}
