package com.xplained.main.sliders.slides.elements;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.sliders.slides.elements.ElementRequestBody;
import com.xplained.main.dto.user.UserDTO;
import com.xplained.main.sliders.SliderRepository;
import com.xplained.main.sliders.slides.Slide;
import com.xplained.main.sliders.slides.SlideRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ElementService {
    private final ElementRepository elementRepository;
    private final SlideRepository slideRepository;
    private final SliderRepository sliderRepository;
    private final AuthService authService;


    public List<Element> getElements(Long slideId) {

        UserDTO user = authService.getCurrentUser();

        Slide slide = slideRepository.findById(slideId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Slide not found"));

        if (!sliderRepository.existsByIdAndUserId(slide.getSliderId(), user.getId())) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Slider not found");

        return elementRepository.findAllBySlideId(slideId);
    }

    public void deleteAllElementsWithinTheSlide(Long slideId) {
        elementRepository.deleteBySlideId(slideId);
    }

    public void createSlideElements(Long slideId, List<ElementRequestBody> requestBody) {

        UserDTO user = authService.getCurrentUser();

        Slide slide = slideRepository.findById(slideId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Slide not found"));

        if (!sliderRepository.existsByIdAndUserId(slide.getSliderId(), user.getId())) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Slider not found");

        deleteAllElementsWithinTheSlide(slideId);

        requestBody.forEach(element -> {
            elementRepository.save(Element.builder()
                            .id(element.getId())
                            .slideId(slide.getId())
                            .type(element.getType())
                            .data(element.getData())
                    .build());
        });
    }
}
