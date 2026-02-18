package com.example.Post.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.Post.DTO.PostResponseDTO;

public interface PostFetchService {

    Page<PostResponseDTO> getNearbyPosts(Double lat, Double lng, Double radius, Pageable pageable);


}
