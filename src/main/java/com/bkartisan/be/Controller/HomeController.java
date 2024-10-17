package com.bkartisan.be.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

import com.bkartisan.be.Service.OrderService;



@RestController
public class HomeController {


    private OrderService orderService;

    public HomeController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/home") 
    public ResponseEntity<Integer> entry() {
        return ResponseEntity.ok(1);
    }
}
