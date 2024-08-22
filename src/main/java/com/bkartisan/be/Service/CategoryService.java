package com.bkartisan.be.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkartisan.be.Repository.CategoryRepository;
import com.bkartisan.be.Entity.Category;

@Service
public class CategoryService {
    final private int GIFT_ID = 85;

    CategoryRepository repo;

    @Autowired
    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public List<Category> getGifts() {
        return repo.findByCategoryParent(GIFT_ID);
    }
}
