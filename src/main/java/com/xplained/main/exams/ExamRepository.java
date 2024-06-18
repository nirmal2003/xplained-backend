package com.xplained.main.exams;

import com.xplained.main.dto.exams.ExamResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    Long countByUserId(Long userId);

    @Query("SELECT new com.xplained.main.dto.exams.ExamResponse(e.id, e.title, e.image, e.isTextEnabled, e.duration, e.isPublished, e.createdAt) FROM Exam e WHERE e.userId = :userId ORDER BY e.createdAt DESC")
    List<ExamResponse> findAllByUserIdOrderByCreatedAtDesc(@Param("userId") Long userId);

    Optional<Exam> findByIdAndUserId(Long id, Long userId);
    Boolean existsByIdAndUserId(Long id, Long userId);
}
