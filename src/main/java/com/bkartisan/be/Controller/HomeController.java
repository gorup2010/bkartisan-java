package com.bkartisan.be.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

import com.bkartisan.be.Service.OrderService;
import com.bkartisan.be.Constant.OrderStatus;
import com.bkartisan.be.Dto.OrderBuyerQueryResult;

import java.util.List;



@RestController
public class HomeController {


    private OrderService orderService;

    public HomeController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/home") 
    public ResponseEntity<List<OrderBuyerQueryResult>> entry() {
        return ResponseEntity.ok(orderService.getBuyerOrders("SaulGoofman", OrderStatus.PROCESSING));
    }
}
