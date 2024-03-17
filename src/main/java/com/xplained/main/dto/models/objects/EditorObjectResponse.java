package com.xplained.main.dto.models.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditorObjectResponse {
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

    private LocalDateTime createdAt;
}
