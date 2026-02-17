package com.example.Post.service.Impl;



import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Post.entity.Comment;
import com.example.Post.entity.Post;
import com.example.Post.repository.CommentRepository;
import com.example.Post.service.CommentService;
import com.example.Post.service.PostService;

import jakarta.persistence.EntityManager;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
private final CommentRepository commentRepository;
private final PostService postService;
private final EntityManager entityManager;

@Transactional
@Override
public Comment addComment(Long postId, String text,String authorEmail){
    Comment comment=new Comment();
    comment.setText(text);
        comment.setAuthorEmail(authorEmail); // save user
        comment.setPost(entityManager.getReference(Post.class,postId));
Comment savedComment=commentRepository.save(comment);
postService.incrementComment(postId);
return savedComment;



}
@Override
public List<Comment> getCommentByPost(Long postId){
    return commentRepository.findByPostId(postId);
}
}
