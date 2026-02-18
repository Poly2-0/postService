package com.example.Post.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.Post.DTO.PostResponseDTO;
import org.springframework.http.MediaType;
import com.example.Post.service.PostService;

import org.springframework.data.domain.Pageable;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    // create post
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostResponseDTO> createPost(
            @RequestParam("caption") String caption,
            @RequestParam("file") MultipartFile file,
            @RequestParam Double lat,
            @RequestParam Double lng,
            @RequestParam String mediaType,
            Authentication auth) {
        String authorEmail = auth.getName();
        PostResponseDTO response = postService.createPost(caption, file,lat,lng,mediaType, authorEmail);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/feed")
    public ResponseEntity<Page<PostResponseDTO>> getFeed(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(postService.getfeed(pageable));
    }
    // get post by post id

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPostById(
            @PathVariable Long id,
            Authentication auth) {
     
        PostResponseDTO dto = postService.getPostById(id);
        return ResponseEntity.ok(dto);
    }

    // test jwt
    @GetMapping("/success")
    public ResponseEntity<String> success(Authentication auth) {
        
        String email = auth.getName();
        String response = "Hello " + email + ", login successful!";
        return ResponseEntity.ok(response);
    }

}
