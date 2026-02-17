package com.example.Post.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Post.constant.InteractionType;
import com.example.Post.entity.Interaction;


public interface InteractionRepository extends JpaRepository<Interaction ,Long> {
//check duplicate 
Optional<Interaction> findByUserEmailAndPostIdAndType(String userEmail ,Long postId,InteractionType type);
   long countByPostIdAndType(Long postId, InteractionType type); 

}
