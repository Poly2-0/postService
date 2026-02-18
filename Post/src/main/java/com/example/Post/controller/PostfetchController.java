package com.example.Post.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
  
  @GetMapping("/feed")
  public ResponseEntity<Page<PostResponseDTO>> getFeed(
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "10") int size,
          Authentication auth) {
      
      Pageable pageable = PageRequest.of(page, size);
      return ResponseEntity.ok(postFetchService.getfeed(pageable));
  }
  
  @GetMapping("/nearby")
  public ResponseEntity<Page<PostResponseDTO>> getNearbyPosts(
        @RequestParam double lat,
          @RequestParam double lng,
          @RequestParam double radius,
          Pageable pageable,
          Authentication auth
    )
    {
        return ResponseEntity.ok(postFetchService.getNearbyPosts(lat,lng,radius,pageable));
    }
}
