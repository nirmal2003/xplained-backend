package com.xplained.main.dto.models.objects.animations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimationResponse {
    private Long id;
    private Integer type;
    private Double start;
    private Double end;
    private Double duration;
    private LocalDateTime createdAt;
}
