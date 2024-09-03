package com.bkartisan.be.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.bkartisan.be.Dto.LoginRequestDTO;
import com.bkartisan.be.Dto.RegisterRequestDTO;
import com.bkartisan.be.Dto.UserProfileDTO;
import com.bkartisan.be.Entity.User;
import com.bkartisan.be.Service.CartService;
import com.bkartisan.be.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@RestController
public class AuthController {

    private UserService userService;
    private CartService cartService;

    @Autowired
    public AuthController(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDTO user) {
        userService.registerUser(user);
        return ResponseEntity.ok("Registered successfully!");
    }

    @PostMapping("login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDTO user, HttpServletRequest request,
            HttpServletResponse response) {
        String role = userService.verify(user, request, response);
        return ResponseEntity.ok(role);
    }

    @GetMapping("profile")
    public ResponseEntity<UserProfileDTO> getUserProfile(Principal principal) throws Exception {
        if (principal == null || principal.getName() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        Integer totalPrice = cartService.getTotalPrice(username);
        Integer cartItems = cartService.getTotalItems(username);
        return ResponseEntity.ok(new UserProfileDTO(user, totalPrice, cartItems));
    }
    

    @PostMapping("password")
    public String changePassword() {

        return "entity";
    }

}
