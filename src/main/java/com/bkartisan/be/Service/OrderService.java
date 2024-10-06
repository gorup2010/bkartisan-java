package com.bkartisan.be.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkartisan.be.Dto.CreatePaymentRequestDTO;
import com.bkartisan.be.Repository.OrderRepository;

@Service
public class OrderService {
    
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveOrder(CreatePaymentRequestDTO createPaymentRequestDTO) {
        
    }

}
