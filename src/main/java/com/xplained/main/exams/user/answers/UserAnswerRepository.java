package com.xplained.main.exams.user.answers;

import com.xplained.main.dto.exams.user.ExamResultsResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    Optional<UserAnswer> findByUserExamIdAndQuestionId(Long userExamId, Long questionId);
    Optional<UserAnswer> findByQuestionId(Long questionId);

    List<UserAnswer> findAllByQuestionIdAndType(Long questionId, Integer type);

    @Query("SELECT u FROM UserAnswer u JOIN Choice c ON u.mcqAnswer = c.id WHERE u.questionId = :questionId AND u.type = 1 AND u.mcqAnswer = c.id AND c.isAnswer = true")
    Optional<UserAnswer> findUserAnswer(@Param("questionId") Long questionId);

    Long countByUserExamIdAndIsCorrect(Long userExamId, Boolean isCorrect);

    Long countByUserExamIdAndIsCorrectAndType(Long userExamId, Boolean isCorrect, Integer type);

    Long countByUserExamId(Long userExamId);

    Boolean existsByQuestionId(Long questionId);
}
