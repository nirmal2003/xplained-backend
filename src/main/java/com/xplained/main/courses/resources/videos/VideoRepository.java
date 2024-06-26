package com.xplained.main.courses.resources.videos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findByIdAndUserId(Long id, Long userId);

    List<Video> findAllByUserId(Long userId);
}
