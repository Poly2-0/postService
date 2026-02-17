package com.example.Post.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.Post.service.InteractionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/Interactions")
@RequiredArgsConstructor
public class InteractionController {
    private final InteractionService interactionService;

    @PostMapping("/posts/{postId}/like")

    public ResponseEntity<String> toggleLike(@PathVariable Long postId,
            Authentication auth) {
        String email = auth.getName();
        String result = interactionService.toggleLike(email, postId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/posts/{postId}/dislike")
    public ResponseEntity<String> toggleDislike(@PathVariable Long postId,
            Authentication auth) {
        String email = auth.getName();
        String result = interactionService.toggleDislike(email, postId);
        return ResponseEntity.ok(result);
    }
}
