package com.bkartisan.be.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bkartisan.be.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    
}
