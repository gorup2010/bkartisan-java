package com.bkartisan.be.Repository;

import java.util.List;

import com.bkartisan.be.Dto.ProductFilterForAdminPageDTO;
import com.bkartisan.be.Entity.Product;

public interface ProductRepositoryCustom {
    List<Product> findProductsByFilters(ProductFilterForAdminPageDTO filter);
}
