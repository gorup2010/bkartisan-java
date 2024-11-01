package com.bkartisan.be.Service;

import org.springframework.stereotype.Service;

import com.bkartisan.be.Dto.CreateCommentDTO;
import com.bkartisan.be.Entity.Comment;
import com.bkartisan.be.Entity.Product;
import com.bkartisan.be.Repository.CommentRepository;

import jakarta.persistence.EntityManager;

@Service
public class CommentService {

    private CommentRepository commentRepo;
    private EntityManager entityManager;

    public CommentService(CommentRepository commentRepo, EntityManager entityManager) {
        this.entityManager = entityManager;
        this.commentRepo = commentRepo;
    }

    public void createComment(CreateCommentDTO createCommentDTO, String writer) {
        System.out.println(createCommentDTO);
        Product product = entityManager.getReference(Product.class, createCommentDTO.productId());
        Comment parent = null;
        if (createCommentDTO.parentId() != null) {
            parent = entityManager.getReference(Comment.class, createCommentDTO.parentId());
        }

        Comment comment = Comment.builder().numberOfStars(createCommentDTO.numberOfStars())
                .content(createCommentDTO.content())
                .product(product)
                .parentComment(parent)
                .writer(writer)
                .build();
        commentRepo.save(comment);
    }
}
