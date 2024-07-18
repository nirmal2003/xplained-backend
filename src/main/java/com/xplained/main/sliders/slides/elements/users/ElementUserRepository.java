package com.xplained.main.sliders.slides.elements.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ElementUserRepository extends JpaRepository<ElementUser, Long> {
    Optional<ElementUser> findByElementIdAndUserId(Long elementId, Long userId);

    Optional<ElementUser> findByElementId(Long elementId);
}
