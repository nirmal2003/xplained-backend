package com.xplained.main.sliders.slides;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sliders/slides")
@RequiredArgsConstructor
public class SlideController {
    private final SlideService slideService;


    @GetMapping("/{sliderId}")
    public List<Slide> getSlides(@PathVariable Long sliderId) {
        return slideService.getSlides(sliderId);
    }

    @PostMapping("/{sliderId}")
    public Slide createSlide(@PathVariable Long sliderId) {
        return slideService.createSlide(sliderId);
    }

    @DeleteMapping("/{id}")
    public void deleteSlide(@PathVariable Long id) {
        slideService.deleteSlide(id);
    }
}
