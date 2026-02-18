package com.example.Post.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Post.DTO.PostResponseDTO;
import com.example.Post.service.PostFetchService;


@RestController
@RequestMapping("/api/posts")
public class PostfetchController {

  private final PostFetchService postFetchService;
  public PostfetchController(PostFetchService postFetchService){
    this.postFetchService=postFetchService;
  }
    @GetMapping("/nearby")
    public Page<PostResponseDTO> getNearbyPosts(
        @RequestParam double lat,
          @RequestParam double lng,
          @RequestParam double radius,
          Pageable pageable
    )
    {
        return postFetchService.getNearbyPosts(lat,lng,radius,pageable);
    }
}
