package com.xplained.main.exams.user.answers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    Optional<UserAnswer> findByUserExamIdAndQuestionId(Long userExamId, Long questionId);
}
