package com.bkartisan.be.Controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bkartisan.be.Dto.CreateCommentDTO;
import com.bkartisan.be.Entity.Product;
import com.bkartisan.be.Repository.ProductRepository;
import com.bkartisan.be.Service.CommentService;



@RestController
public class HomeController {

    CommentService commentService;

    public HomeController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/home") 
    public ResponseEntity<Void> createComment(@RequestBody CreateCommentDTO createCommentDTO, Principal principal) {
        commentService.createComment(createCommentDTO, principal.getName());
        return ResponseEntity.created(null).build();
    }
}
