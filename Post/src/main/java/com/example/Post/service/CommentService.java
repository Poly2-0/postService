package com.example.Post.service;

import java.util.List;

import com.example.Post.entity.Comment;

public interface CommentService {
Comment addComment(Long postId, String text,String authorEmail);
List<Comment> getCommentByPost(Long postId);
}
