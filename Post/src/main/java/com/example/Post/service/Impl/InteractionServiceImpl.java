package com.example.Post.service.Impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Post.constant.InteractionType;
import com.example.Post.entity.Interaction;
import com.example.Post.entity.Post;
import com.example.Post.repository.InteractionRepository;
import com.example.Post.service.InteractionService;
import com.example.Post.service.PostService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.EntityManager;
@Service
@RequiredArgsConstructor

public class InteractionServiceImpl implements InteractionService{
private final InteractionRepository interactionRepository;
private final PostService postService;
private final EntityManager entityManager;
@Transactional 
@Override
public String toggleLike(String userEmail, Long postId){
    Optional <Interaction> existing = interactionRepository.findByUserEmailAndPostIdAndType(userEmail, postId, InteractionType.LIKE);
    if(existing.isPresent()){
        interactionRepository.delete(existing.get());
postService.decrementLikes(postId);
return "post Unliked";
    }
    else{
        Interaction like =new Interaction();
        like.setUserEmail(userEmail);
        like.setPost(entityManager.getReference(Post.class,postId));
        like.setType(InteractionType.LIKE);
        interactionRepository.save(like);

        postService.incrementLikes(postId);
        return "post Liked";
    }

}
@Transactional 
@Override
public String toggleDislike(String userEmail, Long postId){
    Optional <Interaction> existing = interactionRepository.findByUserEmailAndPostIdAndType(userEmail, postId, InteractionType.DISLIKE);
    if(existing.isPresent()){
        interactionRepository.delete(existing.get());
postService.decrementDislikes(postId);
return "post Undisliked";
    }
    else{
        Interaction dislike =new Interaction();
        dislike.setUserEmail(userEmail);
        dislike.setPost(entityManager.getReference(Post.class,postId));
        dislike.setType(InteractionType.DISLIKE);
        interactionRepository.save(dislike);

        postService.incrementDislikes(postId);
        return "post Disliked";
    }

}
}
