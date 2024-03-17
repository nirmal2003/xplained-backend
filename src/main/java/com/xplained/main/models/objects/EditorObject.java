package com.xplained.main.models.objects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class EditorObject {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    private Integer shape;
    private String fill;
    private Double width;
    private Double height;
    private Double yAxis;
    private Double xAxis;
    private Double angle;
    private Double radius;
    private Boolean isFrozen;

    // forces
    private Double velocityX;
    private Double velocityY;
    private Double friction;
    private Double frictionAir;
    private Double restitution;
    private Double density;
    private Double mass;
    private Double inertia;

//    private Boolean isGroup;

//    @ElementCollection
//    private List<Long> objects;

    private Long userId;
    private Long modelId;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
