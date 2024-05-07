package com.xplained.main.courses.enrolled;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrolledCourseRepository extends JpaRepository<EnrolledCourse, Long> {
    List<EnrolledCourse> findAllByUserId(Long userId);

    boolean existsByCourseIdAndUserId(Long courseId, Long userId);
}
