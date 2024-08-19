package com.bkartisan.be.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bkartisan.be.Entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    
}
