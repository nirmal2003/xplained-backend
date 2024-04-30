package com.xplained.main.dto.models.objects;

import lombok.Data;

@Data
public class EditorObjectRequestBody {
    private String name;
    private Integer shape;
    private String fill;
    private Double width;
    private Double height;
    private Double top;
    private Double left;
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
}
