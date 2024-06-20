package com.xplained.main.exams.questions;

import com.xplained.main.dto.exams.question.QuestionAdminResponse;
import com.xplained.main.dto.exams.question.QuestionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q.id FROM Question q WHERE q.examId = :examId ORDER BY q.index ASC")
    List<Long> findAllByExamId(@Param("examId") Long examId);

    @Query("SELECT q.id FROM Question q WHERE q.examId = :examId AND q.type = 1 ORDER BY q.index ASC")
    List<Long> findAllMcqByExamId(@Param("examId") Long examId);

    Optional<Question> findByExamIdAndIndex(Long examId, Integer index);

    Long countByExamIdAndType(Long examId, Integer type);

    Long countByExamId(Long examId);

    @Query("SELECT q.id FROM Question q WHERE q.examId = :examId AND q.type = :type")
    List<Long> findAllIds(@Param("examId") Long examId, @Param("type") Integer type);

    void deleteByExamIdAndType(Long examId, Integer type);

    @Query("SELECT new com.xplained.main.dto.exams.question.QuestionAdminResponse(q.id, a.textAnswer) FROM Question q JOIN UserAnswer a ON a.questionId = q.id WHERE q.examId = :examId ORDER BY q.createdAt ASC")
    List<QuestionAdminResponse> getAllByExamId(@Param("examId") Long examId);

//    @Query("SELECT q.id FROM Question q JOIN UserAnswer a ON a.questionId = q.")
//    List<Long> getUnAnsweredQuestions(@Param("examId") Long examId);
}
