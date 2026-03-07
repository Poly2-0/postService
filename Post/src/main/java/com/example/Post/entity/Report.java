package com.example.Post.entity;
import com.example.Post.constant.ReportCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"post_id","user_email"})

})

public class Report {
@Id
@GeneratedValue(strategy =GenerationType.IDENTITY)
private Long id;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name="post_id")
private Post post;
private String reporterEmail;
@Enumerated(EnumType.STRING)
private ReportCategory category;
private String reason;

}