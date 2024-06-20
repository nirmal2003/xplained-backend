package com.xplained.main.exams.user;

import com.xplained.main.dto.exams.user.UserExamAdminResponse;
import com.xplained.main.dto.exams.user.UserExamResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserExamRepository extends JpaRepository<UserExam, Long> {

//    @Query("SELECT new com.xplained.main.dto.exams.user.UserExamResponse(u.id, e.id, e.title, CASE WHEN u.duration < 500 THEN true ELSE false END, u.duration, e.createdAt) FROM UserExam u JOIN Exam e ON e.id = u.examId  WHERE u.userId = :userId ORDER BY u.createdAt DESC")
    @Query("SELECT new com.xplained.main.dto.exams.user.UserExamResponse(u.id, e.id, e.title, " +
            "CASE WHEN (SELECT count(ua) FROM UserAnswer ua WHERE ua.userExamId = u.id) = (SELECT count(q) FROM Question q WHERE q.examId = e.id) THEN true ELSE false END," +
            "CASE WHEN ((SELECT count(ua) FROM UserAnswer ua WHERE ua.userExamId = u.id) != (SELECT count(q) FROM Question q WHERE q.examId = e.id)) AND u.duration > 0 THEN true ELSE false END  " +
            ",u.duration, e.createdAt) FROM UserExam u JOIN Exam e ON e.id = u.examId  WHERE u.userId = :userId ORDER BY u.createdAt DESC")
    List<UserExamResponse> findAllByUserIdOrderByCreatedAtDesc(@Param("userId") Long userId);

    Optional<UserExam> findByUserIdAndExamId(Long userId, Long examId);

    Boolean existsByUserIdAndExamId(Long userId, Long examId);

    @Query("SELECT new com.xplained.main.dto.exams.user.UserExamAdminResponse(u.name, ue.createdAt) FROM UserExam ue JOIN User u ON ue.userId = u.id WHERE ue.examId = :examId ORDER BY ue.createdAt DESC")
    List<UserExamAdminResponse> getAllExamsByAdmin(@Param("examId") Long examId);
}
