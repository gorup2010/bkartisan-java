package com.bkartisan.be.Dto;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.bkartisan.be.Entity.Product;

@Service
public class ProductsItemForHomePageMapper implements Function<Product, ProductsItemForHomePage> {
    @Override
    public ProductsItemForHomePage apply(Product product) {
        return new ProductsItemForHomePage(product.getProductId(), product.getPrice(), product.getName(),
                product.getSeller(), product.getCoverImage(), product.getNumberOfStar(), product.getNumberOfRating(),
                product.getDiscount());
    }
}
