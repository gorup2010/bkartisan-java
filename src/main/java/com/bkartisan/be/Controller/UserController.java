package com.bkartisan.be.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.bkartisan.be.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("api/v1/users")
public class UserController {
    
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }




    @Operation(summary = "Change user from buyer to seller", tags = { "User" }, responses = {
        @ApiResponse(responseCode = "204", description = "Successful operation", content = @Content()),
        @ApiResponse(responseCode = "401", description = "Unauthorized request", content = @Content()),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })

    @PatchMapping("user/seller")
    public ResponseEntity<Void> registerSeller() {
        long begin = System.currentTimeMillis();
        userService.registerSeller();
        System.out.println("Time taken: " + (begin - System.currentTimeMillis()));
        return ResponseEntity.noContent().build();
    }
    
}
