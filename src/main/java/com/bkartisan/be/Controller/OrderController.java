package com.bkartisan.be.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkartisan.be.Constant.OrderStatus;
import com.bkartisan.be.Dto.OrderBuyerDTO;
import com.bkartisan.be.Dto.OrderBuyerQueryResult;
import com.bkartisan.be.Service.OrderService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    


    @Operation(summary = "Get orders of a buyer", tags = { "Order" }, responses = {})

    @GetMapping("buyer")
    public ResponseEntity<List<OrderBuyerQueryResult>> getBuyerOrders(@RequestParam OrderStatus status, Principal principal) {
        return ResponseEntity.ok(orderService.getBuyerOrders(principal.getName(), status));
    }
    
}
