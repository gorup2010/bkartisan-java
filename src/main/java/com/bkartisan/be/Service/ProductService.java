package com.bkartisan.be.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bkartisan.be.Repository.ProductRepository;
import com.bkartisan.be.Entity.Product;

@Service
public class ProductService {
    ProductRepository repo;

    @Autowired
    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getProducts(String searchTerm, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return repo.findByNameContaining(searchTerm, pageRequest).getContent();
    }

    public Product getProduct(Integer id) {
        return repo.findById(id).orElse(null);
    }
}
