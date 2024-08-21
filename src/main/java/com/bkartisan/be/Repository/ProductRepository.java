package com.bkartisan.be.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bkartisan.be.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByNameContaining(String searchTerm, Pageable pageable);
}