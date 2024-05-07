package com.xplained.main.courses.modules;

import com.xplained.main.dto.courses.modules.CourseModuleResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseModuleRepository extends JpaRepository<CourseModule, Long> {

    @Query("SELECT new com.xplained.main.dto.courses.modules.CourseModuleResponse(m.id, m.name, m.createdAt) FROM CourseModule m WHERE m.courseId = :courseId ORDER BY m.createdAt ASC")
    List<CourseModuleResponse> findAllByCourseId(@Param("courseId") Long courseId, Pageable pageable);

    Long countByCourseId(Long courseId);
}
