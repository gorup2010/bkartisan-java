package com.bkartisan.be.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkartisan.be.Service.OrderService;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

}
