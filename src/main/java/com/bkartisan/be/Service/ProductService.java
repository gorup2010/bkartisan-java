package com.bkartisan.be.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bkartisan.be.Repository.ProductRepository;
import com.bkartisan.be.Dto.ProductFilterForAdminPageDTO;
import com.bkartisan.be.Entity.Product;

@Service
public class ProductService {
    ProductRepository productRepo;

    @Autowired
    public ProductService(ProductRepository repo) {
        this.productRepo = productRepo;
    }

    public List<Product> getProducts(String searchTerm, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return productRepo.findByNameContaining(searchTerm, pageRequest);
    }

    public Product getProduct(Integer id) {
        return productRepo.findById(id).orElse(null);
    }

    public List<Product> getProductsForAdminPage(ProductFilterForAdminPageDTO filter) {
        return productRepo.findProductsByFilters(filter);
    }
}
