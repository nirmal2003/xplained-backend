package com.xplained.main.sliders;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.sliders.SliderRequestBody;
import com.xplained.main.dto.user.UserDTO;
import com.xplained.main.sliders.slides.SlideRepository;
import com.xplained.main.sliders.slides.SlideService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SliderService {
    private final SliderRepository sliderRepository;
    private final AuthService authService;
    private final SlideService slideService;

    public List<Slider> getSliders() {
        UserDTO user = authService.getCurrentUser();

        return sliderRepository.findAllByUserId(user.getId());
    }

    public Slider createSlider() {
        UserDTO user = authService.getCurrentUser();

        Slider slider = sliderRepository.saveAndFlush(Slider.builder()
                .userId(user.getId())
                .title("Untitled " + sliderRepository.countByUserId(user.getId()))
                .image("")
                .build());

        slideService.createSlide(slider.getId());

        return slider;
    }

    public void updateSlider(Long id, SliderRequestBody requestBody) {
        UserDTO user = authService.getCurrentUser();

        Slider slider = sliderRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Slider not found"));

        if (requestBody.getTitle() != null) slider.setTitle(requestBody.getTitle());
        if (requestBody.getImage() != null) slider.setImage(requestBody.getImage());

        sliderRepository.save(slider);
    }

    public void deleteSlider(Long id) {
        UserDTO user = authService.getCurrentUser();

        Slider slider = sliderRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Slider not found"));

        sliderRepository.delete(slider);
    }
}
