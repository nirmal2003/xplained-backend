package com.xplained.main.courses.modules.lessons.resources;

import com.xplained.main.dto.courses.modules.lessons.resources.LessonResourcesResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonResourcesRepository extends JpaRepository<LessonResources, Long> {

    //    @Query("SELECT new com.xplained.main.dto.courses.modules.lessons.resources.LessonResourcesResponse(r.id, r.type, r.name, " +
//            "CASE WHEN r.type = 3 THEN COALESCE((SELECT ue.progress FROM UserExam ue WHERE ue.examId = r.examId AND ue.userId = :userId), '') ELSE '' END " +
//            ", r.createdAt) FROM LessonResources r JOIN User u ON u.id = :userId WHERE r.lessonId = :lessonId ORDER BY r.createdAt ASC")
    @Query("SELECT new com.xplained.main.dto.courses.modules.lessons.resources.LessonResourcesResponse(r.id, r.type, r.name, " +
            "CASE WHEN r.type = 3 THEN COALESCE(CAST((SELECT ue.progress FROM UserExam ue WHERE ue.examId = r.examId AND ue.userId = :userId) AS STRING), '0') ELSE '0' END " +
            ", r.createdAt) FROM LessonResources r JOIN User u ON u.id = :userId WHERE r.lessonId = :lessonId ORDER BY r.createdAt ASC")
    List<LessonResourcesResponse> findAllByLessonIdOrderByCreatedAtAsc(@Param("lessonId") Long lessonId, @Param("userId") Long userId);

    Long countByLessonId(Long lessonId);
}
