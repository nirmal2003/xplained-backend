package com.xplained.main.courses.modules.lessons.resources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonResourcesRepository extends JpaRepository<LessonResources, Long> {
    List<LessonResources> findAllByLessonIdOrderByCreatedAtAsc(Long lessonId);

    Long countByLessonId(Long lessonId);
}
