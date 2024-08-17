package com.bkartisan.be.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bkartisan.be.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByNameContaining(String searchTerm, Pageable pageable);
}