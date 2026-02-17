package com.example.Post.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Post.entity.Comment;
import com.example.Post.service.Impl.CommentServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;


    //adding a cooment 
    @PostMapping("/add")
    public ResponseEntity<Comment> addComment(
       
        @RequestParam Long postId,
        @RequestParam String text,
    Authentication auth){
        String email=auth.getName();
            return ResponseEntity.ok(commentServiceImpl.addComment( postId, text,email));
        }

        // get comment by postid
        @GetMapping("/post/{postId}")
        public ResponseEntity<List<Comment>> getComments(@PathVariable Long postId, Authentication auth  ){
            return ResponseEntity.ok(commentServiceImpl.getCommentByPost(postId));
        }

}
