package com.bkartisan.be.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.bkartisan.be.Dto.LoginRequestDTO;
import com.bkartisan.be.Dto.RegisterRequestDTO;
import com.bkartisan.be.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AuthController {

    private UserService service;

    @Autowired
    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDTO user) {
        service.registerUser(user);
        return ResponseEntity.ok("Registered successfully!");
    }

    @PostMapping("login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDTO user, HttpServletRequest request,
            HttpServletResponse response) {
        service.verify(user, request, response);

        return ResponseEntity.ok("Login Successfully!");
    }

    @PostMapping("logout")
    public String logoutUser() {

        return "entity";
    }

    @PostMapping("password")
    public String postMethodName() {

        return "entity";
    }

}
