package com.xplained.main.dto.models.objects.animations;

import lombok.Data;

@Data
public class AnimationRequestBody {
    private Integer type;
    private Double start;
    private Double end;
    private Double duration;
    private Double angle;
    private Double xAxis;
    private Double yAxis;
}
