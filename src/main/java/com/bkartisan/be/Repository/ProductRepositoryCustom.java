package com.bkartisan.be.Repository;

import java.util.List;

import com.bkartisan.be.Dto.ProductFilterForAdminPageDTO;
import com.bkartisan.be.Dto.ProductFilterForSellerPageDTO;
import com.bkartisan.be.Entity.Product;

public interface ProductRepositoryCustom {
    List<Product> findByFilterInAdminPage(ProductFilterForAdminPageDTO filter);
    List<Product> findByFilterInSellerPage(ProductFilterForSellerPageDTO filter, String username);
}
