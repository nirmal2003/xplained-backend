package com.xplained.main.courses.enrolled.progress.resources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompletedResourcesRepository extends JpaRepository<CompletedResources, Long> {
    Long countByLessonId(Long lessonId);

    Boolean existsByResourcesId(Long resourcesId);
}
