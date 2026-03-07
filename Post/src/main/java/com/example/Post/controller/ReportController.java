package com.example.Post.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Post.constant.ReportCategory;
import com.example.Post.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
  private final ReportService reportService;
   public ReportController(ReportService reportService){
    this .reportService=reportService;
   }
   //api to submit report
   @PostMapping("/{postId}")
   public ResponseEntity<String> submitReport(
    @PathVariable Long postId,
    @RequestParam ReportCategory category,
    @RequestParam(required =false) String reason,
    Authentication auth
   ){
    try{
    //get reporter email from security context
    if(auth==null  || ! auth.isAuthenticated()){
        return ResponseEntity.status(401).body("Error: Unauthorized. Please login.");
    }
    String reporterEmail=auth.getName();
      // call service implementation
      String result = reportService.reportPost(postId, reporterEmail, category, reason);
      //return response on service result
      if(result.contains("already repported")){
        return ResponseEntity.status(409).body(result);
      }
      return ResponseEntity.ok(result);
   }
   catch(RuntimeException e){
    return ResponseEntity.status(404).body("Error" + e.getMessage());
   }
   catch(Exception e ){
    return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
   }
   }
}
