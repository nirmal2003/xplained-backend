package com.xplained.main.courses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Boolean existsByIdAndUserId(Long id, Long userId);
}
