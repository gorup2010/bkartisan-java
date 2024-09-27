package com.bkartisan.be.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
public class HomeController {
    @PostMapping("/home") 
    public String index(@RequestBody String name) {
        return "Welcome to ";
    }
}
