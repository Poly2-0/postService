package com.example.Post.service.Impl;



import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Post.DTO.PostResponseDTO;
import com.example.Post.entity.Post;
import com.example.Post.repository.PostRepository;
import com.example.Post.service.PostFetchService;
@Service
public class PostFetchServiceImpl implements PostFetchService{
private final  PostRepository postRepository;
private final ModelMapper modelMapper;

    public PostFetchServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }
    
    @Override
    public Page<PostResponseDTO> getfeed(Pageable pageable){
        Page<Post> posts = postRepository.findAllByOrderByCreatedAtDesc(pageable);
        return posts.map(this::mapToDTO);
    }
    
    @Override
 public Page<PostResponseDTO> getNearbyPosts( Double lat,Double lng, Double radius ,Pageable pageable){
  Page<Post> posts=postRepository.findNearByPosts(lat,lng,radius,pageable);
 return posts.map(this::mapToDTO);
}
private PostResponseDTO mapToDTO(Post post){
    PostResponseDTO dto=modelMapper.map(post, PostResponseDTO.class);
       if (post.getMediaUrl() != null) {
            dto.setMediaUrl("http://localhost:8080/media/" + post.getMediaUrl());
        }

        if (post.getMediaType() != null) {
            dto.setMediaType(post.getMediaType().name());
        }
        return dto;
}
}

