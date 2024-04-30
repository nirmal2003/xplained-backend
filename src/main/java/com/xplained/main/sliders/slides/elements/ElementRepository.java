package com.xplained.main.sliders.slides.elements;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElementRepository extends JpaRepository<Element, Long> {
    void deleteBySlideId(Long slideId);
    List<Element> findAllBySlideId(Long slideId);
}
