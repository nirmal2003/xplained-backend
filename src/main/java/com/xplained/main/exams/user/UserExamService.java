package com.xplained.main.exams.user;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.exams.user.UserExamRequestBody;
import com.xplained.main.exams.Exam;
import com.xplained.main.exams.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserExamService {
    private final UserExamRepository userExamRepository;
    private final AuthService authService;
    private final ExamRepository examRepository;


    public List<UserExam> getAllUserExam() {
        return userExamRepository.findAllByUserIdOrderByCreatedAtDesc(authService.getCurrentUser().getId());
    }

    public UserExam userExamDetails(Long examId) {
        if (!examRepository.existsById(examId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found");

        Optional<UserExam> userExam = userExamRepository.findByUserIdAndExamId(authService.getCurrentUser().getId(), examId);

        return userExam.orElseGet(() -> createUserExam(examId));
    }

    public UserExam createUserExam(Long examId) {
        Exam exam = examRepository.findById(examId).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found"));

        UserExam userExam = UserExam.builder()
                .examId(examId)
                .userId(authService.getCurrentUser().getId())
                .nextIndex(0)
                .duration(exam.getDuration())
                .build();

        return userExamRepository.saveAndFlush(userExam);
    }

    public void updateUserExam(Long examId, UserExamRequestBody requestBody) {
        UserExam exam = userExamRepository.findByUserIdAndExamId(authService.getCurrentUser().getId(), examId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found"));

        if (requestBody.getDuration() != null) exam.setDuration(requestBody.getDuration());
        if (requestBody.getNextIndex() != null) exam.setNextIndex(requestBody.getNextIndex());

        userExamRepository.save(exam);
    }

    public void deleteUserExam(Long examId) {
        UserExam userExam = userExamRepository.findByUserIdAndExamId(authService.getCurrentUser().getId(), examId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found"));

        userExamRepository.delete(userExam);
    }
}
