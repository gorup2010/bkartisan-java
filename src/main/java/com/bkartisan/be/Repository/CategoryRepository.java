package com.bkartisan.be.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bkartisan.be.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByCategoryParent(Integer categoryParent);
    List<Category> findByLevel(Integer level);
}