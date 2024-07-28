package com.xplained.main.courses;

import com.xplained.main.dto.courses.CourseResponse;
import com.xplained.main.dto.courses.CourseSearchResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT new com.xplained.main.dto.courses.CourseResponse(c.id, c.title, c.image, c.isPrivate, c.isPublished, c.isActive) FROM Course c WHERE c.userId = :userId ORDER BY c.createdAt")
    List<CourseResponse> findAllByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT new com.xplained.main.dto.courses.CourseSearchResponse(c.id, c.title, c.image) FROM Course c WHERE LOWER(c.title) LIKE %:title% ORDER BY c.createdAt")
    List<CourseSearchResponse> searchCourseByTitle(@Param("title") String title, Pageable pageable);

    @Query("SELECT new com.xplained.main.dto.courses.CourseSearchResponse(c.id, c.title, c.image) FROM Course c ORDER BY c.createdAt ASC")
    List<CourseSearchResponse> searchCourseByEmptyTitle(Pageable pageable);

    @Query("SELECT new com.xplained.main.dto.courses.CourseSearchResponse(c.id, c.title, c.image) FROM Course c ORDER BY c.createdAt ASC")
    List<CourseSearchResponse> findAllRecommendedCourses(Pageable pageable);

    Optional<Course> findByIdAndUserId(Long id, Long userId);

    Boolean existsByIdAndUserId(Long id, Long userId);

    Long countByUserId(Long userId);
}
