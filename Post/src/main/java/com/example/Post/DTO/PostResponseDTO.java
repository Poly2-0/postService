package com.example.Post.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PostResponseDTO {
   
    private Long id;
    private String caption;
    private String mediaUrl;
    private String mediaType;

    private Long likeCount;
    private Long dislikeCount;

    private String authorEmail;
    
    private LocalDateTime createdAt;
}
