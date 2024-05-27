package com.xplained.main.courses.enrolled.sliders;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.enrolled.sliders.SliderProgressRequestBody;
import com.xplained.main.dto.enrolled.sliders.SliderProgressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SliderProgressService {
    private final SliderProgressRepository sliderProgressRepository;
    private final AuthService authService;


    public SliderProgressResponse getSliderProgress(Long sliderId) {
        Optional<SliderProgress> progress = sliderProgressRepository.findBySliderIdAndUserId(sliderId, authService.getCurrentUser().getId());

        if (progress.isEmpty()) {
            SliderProgress newProgress = sliderProgressRepository.saveAndFlush(SliderProgress.builder()
                            .userId(authService.getCurrentUser().getId())
                            .sliderId(sliderId)
                            .lastCompletedSlide(0)
                    .build());

            return SliderProgressResponse.builder()
                    .lastCompletedSlide(newProgress.getLastCompletedSlide())
                    .build();
        }

        return SliderProgressResponse.builder()
                .lastCompletedSlide(progress.get().getLastCompletedSlide())
                .build();
    }

    public void increaseTheSlide(Long sliderId, SliderProgressRequestBody requestBody) {
        SliderProgress progress = sliderProgressRepository.findBySliderIdAndUserId(sliderId, authService.getCurrentUser().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "slider progress not found"));

        progress.setLastCompletedSlide(requestBody.getLastCompletedSlide());

        sliderProgressRepository.save(progress);
    }
}
