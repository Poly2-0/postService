package com.example.Post.service;

public interface InteractionService {

    String toggleLike(String userEmail,Long postId);
    String toggleDislike(String  userEmail,Long postId);
}
