package com.example.Post.service.Impl;

import com.example.Post.constant.MediaType;



import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Post.DTO.PostResponseDTO;
import com.example.Post.entity.Post;

import com.example.Post.repository.PostRepository;
import com.example.Post.service.PostService;
import com.example.Post.service.StorageService;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    private final StorageService storageService;
    private final ModelMapper modelMapper;

@Override
@Transactional
public PostResponseDTO createPost(String caption,MultipartFile file,String authorEmail){
    //user fetch
   
    //save file
    String fileName =storageService.upload(file);
    //media type
    MediaType type=file.getContentType().contains("video")? MediaType.VIDEO :MediaType.IMAGE;
    //save postentity
    Post post=new Post();
    post.setCaption(caption);
    post.setMediaUrl(fileName);
    post.setMediaType(type);
    post.setAuthorEmail(authorEmail);
    
    
    Post savedPost=postRepository.save(post);
    return mapToDTO(savedPost);
}
// feed 
@Override
public  Page<PostResponseDTO> getfeed(Pageable pageable){

    Page<Post> postPage =postRepository.findAllByOrderByCreatedAtDesc(pageable);
    return postPage.map(this :: mapToDTO);
}
@Override
public PostResponseDTO getPostById(Long id){

    Post post=postRepository.findById(id)
    .orElseThrow(()-> new RuntimeException("Post NOt Found"));
    return mapToDTO(post);
}

private PostResponseDTO mapToDTO(Post post){
    PostResponseDTO dto=modelMapper.map(post, PostResponseDTO.class);
    dto.setMediaUrl("http://localhost:8080/media/" + post.getMediaUrl());
        
        if (post.getAuthorEmail() != null) {
            dto.setAuthorEmail(post.getAuthorEmail());
             
        }
          if (post.getMediaType() != null) {
        dto.setMediaType(post.getMediaType().name());
    }
        
   
        return dto;
}

@Override
public void incrementLikes(Long postId){
    Post post =postRepository.findById(postId)
    .orElseThrow(()-> new RuntimeException("Post not found"));

    post.setLikeCount(post.getLikeCount()+1);
    postRepository.save(post);
}
@Override
public void decrementLikes(Long postId){
    Post post=postRepository.findById(postId)
    .orElseThrow(()-> new RuntimeException("post not found"));

    if(post.getLikeCount()>0){
        post.setLikeCount(post.getLikeCount()-1);
        postRepository.save(post);
    }
}
@Override
public void incrementComment(Long postId){
    Post post =postRepository.findById(postId)
    .orElseThrow(()-> new RuntimeException("Post not found"));
      

    post.setCommentCount(post.getCommentCount()+1);
    postRepository.save(post);

}
@Override
public void incrementDislikes(Long postId){
    Post post =postRepository.findById(postId)
    .orElseThrow(()-> new RuntimeException("Post not found"));

    post.setDislikecount(post.getDislikecount()+1);
    postRepository.save(post);
}
@Override
public void decrementDislikes(Long postId){
    Post post=postRepository.findById(postId)
    .orElseThrow(()-> new RuntimeException("post not found"));

    if(post.getDislikecount()>0){
        post.setDislikecount(post.getDislikecount()-1);
        postRepository.save(post);
    }
}
    

}
