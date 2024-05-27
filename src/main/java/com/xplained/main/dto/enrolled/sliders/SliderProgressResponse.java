package com.xplained.main.dto.enrolled.sliders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SliderProgressResponse {
    private Integer lastCompletedSlide;
}
