package com.example.Post.repository;
import com.example.Post.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report ,Long> {
//check unique constraints
boolean existsByPostIdAndReporterEmail(Long postId, String reporterEmail);
}
