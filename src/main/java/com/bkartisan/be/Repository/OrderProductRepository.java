package com.bkartisan.be.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bkartisan.be.Entity.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
    
}
