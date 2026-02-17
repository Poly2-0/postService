package com.example.Post.entity;

import com.example.Post.constant.InteractionType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="interactions",uniqueConstraints = {@UniqueConstraint(columnNames = {"post_id"})})
@Getter
@Setter
@Data
public class Interaction {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
private String userEmail;
@ManyToOne
@JoinColumn(name="post_id")
private Post post;
@Enumerated(EnumType.STRING)
private InteractionType type;
}
