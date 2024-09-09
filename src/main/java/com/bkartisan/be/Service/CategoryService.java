package com.bkartisan.be.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkartisan.be.Repository.CategoryRepository;
import com.bkartisan.be.Entity.Category;

@Service
public class CategoryService {
    final private int GIFT_ID = 85;

    CategoryRepository categoryRepo;

    @Autowired
    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Category> getGifts() {
        return categoryRepo.findByCategoryParent(GIFT_ID);
    }

    public List<Category> getCategoryByLevel(Integer level) {
        return categoryRepo.findByLevel(level);
    }

    public List<Category> getCategoryHierachies(Integer categoryId) {
        return categoryRepo.getCategoryHierachies(categoryId);
    }
}
