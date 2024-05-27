package com.xplained.main.courses.enrolled.sliders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SliderProgressRepository extends JpaRepository<SliderProgress, Long> {
    Optional<SliderProgress> findBySliderIdAndUserId(Long sliderId, Long userId);
}
