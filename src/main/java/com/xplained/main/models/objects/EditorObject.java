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

    // styling
    private String color;
    private String strokeColor;
    private Float lineWidth;

    // forces
    private Double velocityX;
    private Double velocityY;
    private Double friction;
    private Double frictionStatic;
    private Double frictionAir;
    private Double restitution;
    private Double density;
    private Double mass;
    private Double inertia;

    private Boolean isLineObject;
    private Boolean lineDefault;
    private Long lineId;


    // for lines
    private Long bodyA;
    private Long bodyB;
    private Long bodyADefault;
    private Long bodyBDefault;
    private Double pointAX;
    private Double pointAY;
    private Double pointBX;
    private Double pointBY;
    private Double stiffness;
    private Double damping;
    private Double length;

    // for strings
    private Double segmentWidth;
    private Double segmentHeight;
    private Double segmentCount;
    private Double stringStartY;
    private Double stringStartX;
    private Double stringSpacing;
    private Boolean isStartStatic;
    private Boolean isEndStatic;
    private String collisionCategory;
    private String collisionMask;

    private Boolean isGroupObject;
    private Long groupId;

    @Transient
    private List<Long> constraints;

    private Long userId;
    private Long modelId;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
