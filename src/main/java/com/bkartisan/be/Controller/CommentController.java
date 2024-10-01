package com.bkartisan.be.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkartisan.be.Dto.CreateCommentDTO;
import com.bkartisan.be.Service.CommentService;


@RestController
@RequestMapping("api/v1/comments")
public class CommentController {
    
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping()
    public ResponseEntity<Void> createComment(@RequestBody CreateCommentDTO createCommentDTO, Principal principal) {
        commentService.createComment(createCommentDTO, principal.getName());
        return ResponseEntity.created(null).build();
    }
}
