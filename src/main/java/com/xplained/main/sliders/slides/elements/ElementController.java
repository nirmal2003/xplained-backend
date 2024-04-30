package com.xplained.main.sliders.slides.elements;

import com.xplained.main.dto.sliders.slides.elements.ElementRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sliders/slides/elements")
@RequiredArgsConstructor
public class ElementController {
    private final ElementService elementService;


    @GetMapping("/{slideId}")
    public List<Element> getElements(@PathVariable Long slideId) {
        return elementService.getElements(slideId);
    }

    @PostMapping("/{slideId}")
    public void createSlideElements(@PathVariable Long slideId, @RequestBody List<ElementRequestBody> requestBody) {
        elementService.createSlideElements(slideId, requestBody);
    }
}
