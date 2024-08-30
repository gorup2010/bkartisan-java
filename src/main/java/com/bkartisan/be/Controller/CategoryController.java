package com.bkartisan.be.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.bkartisan.be.Entity.Category;
import com.bkartisan.be.Service.CategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("gifts")
    public ResponseEntity<List<Category>> getGifts() {
        List<Category> gifts = categoryService.getGifts();
        return ResponseEntity.ok(gifts);
    }
}
