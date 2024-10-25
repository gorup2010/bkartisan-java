package com.bkartisan.be.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bkartisan.be.Constant.OrderStatus;
import com.bkartisan.be.Dto.OrderForBuyerPageDTO;
import com.bkartisan.be.Dto.OrderForSellerPageDTO;
import com.bkartisan.be.Service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;




@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    


    @Operation(summary = "Get orders of a buyer", tags = { "Order" }, responses = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = OrderForBuyerPageDTO.class))
        }),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })

    @GetMapping("buyer")
    public ResponseEntity<List<OrderForBuyerPageDTO>> getBuyerOrders(@RequestParam(required = false) OrderStatus status, Principal principal) {
        return ResponseEntity.ok(orderService.getBuyerOrders(principal.getName(), status));
    }
    




    @Operation(summary = "Get orders of a seller", tags = { "Order" }, responses = {
        @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = OrderForSellerPageDTO.class))
        }),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })

    @GetMapping("seller")
    @Secured("seller")
    public ResponseEntity<List<OrderForSellerPageDTO>> getSellerOrders(@RequestParam(required = false) Integer page, 
                                    @RequestParam(required = false) Integer offset, Principal principal) {
        return ResponseEntity.ok(orderService.getSellerOrders(principal.getName(), PageRequest.of(page - 1, offset)));
    }
}
