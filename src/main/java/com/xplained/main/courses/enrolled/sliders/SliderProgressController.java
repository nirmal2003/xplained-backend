package com.xplained.main.courses.enrolled.sliders;

import com.xplained.main.dto.enrolled.sliders.SliderProgressRequestBody;
import com.xplained.main.dto.enrolled.sliders.SliderProgressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sliders/progress")
@RequiredArgsConstructor
public class SliderProgressController {
    private final SliderProgressService sliderProgressService;


    @GetMapping("/{sliderId}")
    public SliderProgressResponse getSliderProgress(@PathVariable Long sliderId) {
        return sliderProgressService.getSliderProgress(sliderId);
    }

    @PutMapping("/{sliderId}")
    public void increaseTheSlide(@PathVariable Long sliderId, @RequestBody SliderProgressRequestBody requestBody) {
        sliderProgressService.increaseTheSlide(sliderId, requestBody);
    }
}
