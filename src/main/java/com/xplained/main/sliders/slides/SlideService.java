package com.xplained.main.sliders.slides;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.user.UserDTO;
import com.xplained.main.sliders.SliderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SlideService {
    private final SlideRepository slideRepository;
    private final SliderRepository sliderRepository;
    private final AuthService authService;

    public List<Slide> getSlides(Long sliderId) {
        UserDTO user = authService.getCurrentUser();

        if (!sliderRepository.existsByIdAndUserId(sliderId, user.getId())) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Slider not found");

        return slideRepository.findAllBySliderId(sliderId);
    }

    public Slide getSlideByIndex(Long sliderId, Integer index) {
        return slideRepository.findAllBySliderIdAndIndex(sliderId, index).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "slide not found"));
    }

    public Slide createSlide(Long sliderId) {
        UserDTO user = authService.getCurrentUser();

        if (!sliderRepository.existsByIdAndUserId(sliderId, user.getId())) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Slider not found");

        Slide slide = Slide.builder()
                .sliderId(sliderId)
                .index(slideRepository.countBySliderId(sliderId))
                .build();

        return slideRepository.saveAndFlush(slide);
    }

    public void deleteSlide(Long id) {
        UserDTO user = authService.getCurrentUser();

        Slide slide = slideRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Slide not found"));

        if (!sliderRepository.existsByIdAndUserId(slide.getSliderId(), user.getId())) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Slider not found");

        slideRepository.delete(slide);
    }
}
