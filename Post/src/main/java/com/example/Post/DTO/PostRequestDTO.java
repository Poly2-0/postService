package com.example.Post.DTO;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class PostRequestDTO {

    private String caption;
    private Double lat;
    private Double lng;
    private String mediaType; // image or vedio
     private MultipartFile file;
}
