package com.example.Post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Post.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment ,Long> {

    List<Comment> findByPostIdOrderByCreatedAtDesc(Long postId);
List<Comment> findByPostId(Long postId);


}
