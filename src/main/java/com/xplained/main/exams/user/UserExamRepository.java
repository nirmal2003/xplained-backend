package com.xplained.main.exams.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserExamRepository extends JpaRepository<UserExam, Long> {
    List<UserExam> findAllByUserIdOrderByCreatedAtDesc(Long userId);

    Optional<UserExam> findByUserIdAndExamId(Long userId, Long examId);

    Boolean existsByUserIdAndExamId(Long userId, Long examId);
}
