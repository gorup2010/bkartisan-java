package com.bkartisan.be.Service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import com.bkartisan.be.Dto.LoginRequestDTO;
import com.bkartisan.be.Dto.RegisterRequestDTO;
import com.bkartisan.be.Entity.User;
import com.bkartisan.be.Repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserService {

    private UserRepository repo;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder encoder;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
            .getContextHolderStrategy();

    @Autowired
    UserService(UserRepository repo, AuthenticationManager authenticationManager, PasswordEncoder encoder) {
        this.encoder = encoder;
        this.repo = repo;
        this.authenticationManager = authenticationManager;
    }

    public void registerUser(RegisterRequestDTO registerRequest) {
        if (repo.existsById(registerRequest.username())) {
            throw new RuntimeException("User already exists");
        }
        String encryptedPassword = encoder.encode(registerRequest.password());
        registerRequest.setPassword(encryptedPassword);
        User user = new User(registerRequest);
        repo.save(user);
    }

    public void verify(LoginRequestDTO loginRequest, HttpServletRequest request,
            HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        // Create a new context
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        // // Update SecurityContextHolder and Strategy
        this.securityContextHolderStrategy.setContext(context);
        this.securityContextRepository.saveContext(context, request, response);
    }

    public List<User> getUsers() {
        return repo.findAll();
    }

    public User getUserByUsername(String username) {
        return repo.findById(username).orElse(null);
    }

}
