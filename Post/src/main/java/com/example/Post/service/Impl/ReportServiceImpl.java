package com.example.Post.service.Impl;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Post.constant.PostStatus;
import com.example.Post.constant.ReportCategory;
import com.example.Post.entity.Post;
import com.example.Post.entity.Report;
import com.example.Post.repository.PostRepository;
import com.example.Post.repository.ReportRepository;
import com.example.Post.service.ReportService;

@Service
public class ReportServiceImpl  implements ReportService{
private final ReportRepository reportRepository;
private final PostRepository postRepository;
public ReportServiceImpl(ReportRepository reportRepository , PostRepository postRepository){
    this .postRepository=postRepository;
    this.reportRepository=reportRepository;
}
@Override
@Transactional
public String reportPost(Long postId, String reporterEmail, ReportCategory category,String reason){
    //unique constraints check
    if(reportRepository.existsByPostIdAndReporterEmail(postId, reporterEmail)){
        return " you have already reported with post";

    }
    //fetch post
    Post post =postRepository.findById(postId)
              .orElseThrow(()-> new RuntimeException("post not found with id"));
       //report create 
       Report report = new Report() ;
          report.setPost(post);
          report.setReporterEmail(reporterEmail);
          report.setCategory(category);
          report.setReason(reason);
          reportRepository.save(report);
          // counting offfensive report
          if(category!=ReportCategory.HELPFUL){
             post.setReportCount(post.getReportCount()+1);
             // automatic update status to under review
             if(post.getReportCount()>=2){
                post.setStatus(PostStatus.UNDER_REVIEW);
             }
          }
          postRepository.save(post);
          return "Post has been reported as \" + category + \". Thank you for keeping the community safe.";
}
}
