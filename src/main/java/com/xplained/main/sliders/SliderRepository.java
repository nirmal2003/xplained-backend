package com.xplained.main.sliders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SliderRepository extends JpaRepository<Slider, Long> {

    Optional<Slider> findByIdAndUserId(Long id, Long userId);

    Boolean existsByIdAndUserId(Long id, Long userId);

    List<Slider> findAllByUserId(Long userId);

    Long countByUserId(Long userId);
}
