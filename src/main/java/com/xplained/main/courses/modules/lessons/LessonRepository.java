package com.xplained.main.courses.modules.lessons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {


//    @Query("SELECT new com.xplained.main.courses.modules.lessons.Lesson(l.id, l.moduleId, l.name, l.index," +
//            "COALESCE((SELECT ((COUNT(cr) / COUNT(lr)) * 100)  FROM CompletedResources cr JOIN LessonResources lr ON lr.lessonId = l.id WHERE cr.lessonId = l.id AND cr.userId = :userId), '0')" +
//            ", l.createdAt) FROM Lesson l WHERE l.moduleId = :moduleId ORDER BY l.index ASC")
//    List<Lesson> findAllByModuleIdOrderByIndexAsc(Long moduleId, Long userId);

    @Query("SELECT new com.xplained.main.courses.modules.lessons.Lesson(l.id, l.moduleId, l.name, l.index, " +
            "COALESCE((SELECT ((CAST(COUNT(cr) AS float) / CAST(COUNT(lr) AS float)) * 100) " +
            "FROM LessonResources lr " +
            "LEFT JOIN CompletedResources cr ON cr.resourcesId = lr.id AND cr.userId = :userId " +
            "WHERE lr.lessonId = l.id), 0) " +
            ", l.createdAt) " +
            "FROM Lesson l " +
            "WHERE l.moduleId = :moduleId " +
            "ORDER BY l.index ASC")
    List<Lesson> findAllByModuleIdOrderByIndexAsc(@Param("moduleId") Long moduleId, @Param("userId") Long userId);

    Long countByModuleId(Long moduleId);
}
