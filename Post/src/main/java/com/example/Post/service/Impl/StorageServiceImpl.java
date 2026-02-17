package com.example.Post.service.Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Post.service.StorageService;
@Service
public class StorageServiceImpl implements StorageService{
@Value("${file.upload-dir}")
private String uploadDir;
@Override
public String upload(MultipartFile file) {
try{
    if(file.isEmpty()){
        throw new RuntimeException("file is empty");
    }
    String orginalFileName=file.getOriginalFilename();
    String uniqueFileName=System.currentTimeMillis() + "_" + orginalFileName;

    Path path=Paths.get(uploadDir + uniqueFileName);
    Files.copy(file.getInputStream(),path,StandardCopyOption.REPLACE_EXISTING);


    return uniqueFileName;
}
catch(IOException e){
throw new RuntimeException("Could not save file: " + e.getMessage());
}


}
}
