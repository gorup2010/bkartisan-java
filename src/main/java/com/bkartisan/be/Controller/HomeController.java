package com.bkartisan.be.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bkartisan.be.Entity.User;
import com.bkartisan.be.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HomeController {
    
    private UserService service;

    public HomeController(UserService service) {
        this.service = service;
    }

    @GetMapping("/") 
    public String index(HttpServletRequest request) {
        return "Welcome to " + request.getSession().getId();
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers() {
       return ResponseEntity.ok(service.getUsers());
    }
    
    @GetMapping("users/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = service.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    

}
