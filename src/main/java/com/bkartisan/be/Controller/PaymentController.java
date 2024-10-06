package com.bkartisan.be.Controller;

import java.util.List;
import java.security.Principal;

import com.bkartisan.be.Dto.CreatePaymentRequestDTO;
import com.bkartisan.be.Dto.OrderAtEachShopDTO;
import com.bkartisan.be.Service.OrderService;
import com.bkartisan.be.Service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// TODO: Backend must be deployed in order to intergrate VNPay.
// Currently, it is not deployed so we would return "payment-url" and store the order in database. 
// After that, the frontend would navigate to success payment page.
@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {

    private PaymentService paymentService;
    private OrderService orderService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }




    @Operation(summary = "Checkout order before procceed to payment", tags = { "Payment" }, responses = {
            @ApiResponse(responseCode = "200", content = {
                @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = OrderAtEachShopDTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })

    @GetMapping("checkout")
    public ResponseEntity<List<OrderAtEachShopDTO>> checkoutOrder(Principal principal) {
        List<OrderAtEachShopDTO> orders = paymentService.checkoutOrder(principal.getName());
        return ResponseEntity.ok(orders);
    }




    @Operation(summary = "Create vnpay payment url", tags = { "Payment" }, responses = {
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "text/plain")}),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })

    @PostMapping()
    public String createPaymentUrl(@RequestBody CreatePaymentRequestDTO createPaymentRequestDTO,
            HttpServletRequest request, Principal principal) {
        // String vnpayUrl = paymentService.createPaymentUrl(principal.getName(), request);
        String vnpayUrl = "payment-url";
        orderService.saveOrder(createPaymentRequestDTO);
        return vnpayUrl;
    }

}
