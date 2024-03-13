package com.xplained.main.models.objects.animations.controllers;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class AnimationController {
    @Id
    @GeneratedValue
    private Long id;
    private Long animationId;
    private Long modelId;
    private Integer type;
    private Integer controller;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
