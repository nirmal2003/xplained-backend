package com.xplained.main.sliders;

import com.xplained.main.dto.sliders.SliderRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sliders")
@RequiredArgsConstructor
public class SliderController {
    private final SliderService sliderService;


    @GetMapping
    public List<Slider> getSliders() {
        return sliderService.getSliders();
    }

    @PostMapping
    public Slider createSlider() {
        return sliderService.createSlider();
    }

    @PutMapping("/{id}")
    public void updateSlider(@PathVariable Long id, @RequestBody SliderRequestBody requestBody) {
        sliderService.updateSlider(id, requestBody);
    }

    @DeleteMapping("/{id}")
    public void deleteSlider(@PathVariable Long id) {
        sliderService.deleteSlider(id);
    }
}
