package com.example.Post.service;

import com.example.Post.constant.ReportCategory;
public interface ReportService {
 String reportPost(Long postId,String reporterEmail, ReportCategory category,String reason);
    


}
