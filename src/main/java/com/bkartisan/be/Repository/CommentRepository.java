package com.bkartisan.be.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bkartisan.be.Entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
