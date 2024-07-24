package com.xplained.main.courses.modules.lessons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findAllByModuleIdOrderByIndexAsc(Long moduleId);

    Long countByModuleId(Long moduleId);
}
