package com.bkartisan.be.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkartisan.be.Entity.User;
import com.bkartisan.be.Repository.UserRepository;


@Service
public class UserService {

    UserRepository repo;

    @Autowired
    UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> getUsers() {
        return repo.findAll();
    }

    public User getUserByUsername(String username) {
        return repo.findById(username).orElse(null);
    }

}
