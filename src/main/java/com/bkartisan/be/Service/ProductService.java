package com.bkartisan.be.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bkartisan.be.Repository.ProductRepository;
import com.bkartisan.be.Constant.ErrorMessage;
import com.bkartisan.be.Dto.ProductFilterForAdminPageDTO;
import com.bkartisan.be.Entity.Product;
import com.bkartisan.be.ExceptionHandler.NotFoundException;

@Service
public class ProductService {
    ProductRepository productRepo;

    @Autowired
    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getProducts(String searchTerm, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return productRepo.findByNameContaining(searchTerm, pageRequest);
    }

    public Product getProduct(Integer id) {
        Product product = productRepo.findById(id).orElse(null);
        if (product == null) throw new NotFoundException(ErrorMessage.NOT_FOUND_PRODUCT);
        return product;
    }

    public List<Product> getProductsForAdminPage(ProductFilterForAdminPageDTO filter) {
        return productRepo.findProductsByFilters(filter);
    }
}
