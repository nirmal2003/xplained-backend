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

    // forces
    private Double velocityX;
    private Double velocityY;
    private Double friction;
    private Double frictionAir;
    private Double restitution;
    private Double density;
    private Double mass;
    private Double inertia;
}
