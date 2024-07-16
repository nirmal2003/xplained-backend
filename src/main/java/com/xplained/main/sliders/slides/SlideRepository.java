package com.xplained.main.sliders.slides;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SlideRepository extends JpaRepository<Slide, Long> {
    Integer countBySliderId(Long sliderId);

    List<Slide> findAllBySliderId(Long sliderId);

    Optional<Slide> findAllBySliderIdAndIndex(Long sliderId, Integer index);
}
