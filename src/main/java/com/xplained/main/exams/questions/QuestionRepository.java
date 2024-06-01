package com.xplained.main.exams.questions;

import com.xplained.main.dto.exams.question.QuestionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT new com.xplained.main.dto.exams.question.QuestionResponse(q.id, q.question, q.type, q.createdAt) FROM Question q WHERE q.examId = :examId ORDER BY q.index ASC")
    List<QuestionResponse> findAllByExamId(@Param("examId") Long examId);

    Long countByExamId(Long examId);
}
