package com.bkartisan.be.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkartisan.be.Entity.Product;
import com.bkartisan.be.Repository.ProductRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
public class HomeController {

    private ProductRepository productRepository;

    public HomeController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/home") 
    public Product index(@RequestBody String name) {
        Product product = productRepository.findById(99).orElse(null);
        if (product == null) {
            System.out.println("Product not found");
        }
        return product;
    }
}
