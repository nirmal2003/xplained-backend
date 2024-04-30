package com.xplained.main.models.controllers;

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
public class ObjectForce {
    @Id
    @GeneratedValue
    private Long id;
    private Long modelId;
    private Long objectId;
    private String name;
    private Integer force;
    private Float min;
    private Float max;
    private Float defaultValue;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
