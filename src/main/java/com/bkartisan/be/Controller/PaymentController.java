package com.bkartisan.be.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.bkartisan.be.Dto.OrderAtEachShopDTO;
import com.bkartisan.be.Service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.security.Principal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    
    @GetMapping("checkout")
    public ResponseEntity<List<OrderAtEachShopDTO>> checkoutOrder(Principal principal) {
        List<OrderAtEachShopDTO> orders = paymentService.checkoutOrder(principal.getName());
        return ResponseEntity.ok(orders);
    }
    
    @PostMapping()
    public String createPayment() {
        
        return "entity";
    }
    

}
