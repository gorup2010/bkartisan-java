package com.bkartisan.be.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.servlet.http.HttpServletRequest;


@RestController
public class HomeController {
    @GetMapping("/") 
    public String index(HttpServletRequest request) {
        return "Welcome to " + request.getSession().getId();
    }
}
