package com.xplained.main.courses.enrolled;

import com.xplained.main.dto.courses.enrolled.EnrolledCourseResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrolledCourseRepository extends JpaRepository<EnrolledCourse, Long> {

    @Query("SELECT new com.xplained.main.dto.courses.enrolled.EnrolledCourseResponse(ec.id, c.id, u.id, c.title, c.image, u.name, null, 0, 0, ec.createdAt) FROM EnrolledCourse ec JOIN Course c ON c.id = ec.courseId JOIN User u ON u.id = ec.userId WHERE ec.userId = :userId ORDER BY ec.createdAt DESC")
    List<EnrolledCourseResponse> findAllByUserId(@Param("userId") Long userId);

    boolean existsByCourseIdAndUserId(Long courseId, Long userId);
}
