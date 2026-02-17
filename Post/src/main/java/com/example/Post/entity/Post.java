package com.example.Post.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.example.Post.constant.MediaType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="posts")
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String caption;
   private String authorEmail;  
private String mediaUrl;
@Enumerated(EnumType.STRING)
private MediaType mediaType;
private Long likeCount= (long) 0;
private Long dislikecount=(long) 0;

@CreationTimestamp
private LocalDateTime createdAt;
private int commentCount = 0;

}
