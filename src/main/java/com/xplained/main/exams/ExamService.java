package com.xplained.main.exams;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.exams.ExamRequestBody;
import com.xplained.main.dto.exams.ExamResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final AuthService authService;


    public List<ExamResponse> getAllExamsByCreator() {
        return examRepository.findAllByUserIdOrderByCreatedAtDesc(authService.getCurrentUser().getId());
    }

    public ExamResponse createExam() {
        Exam exam = examRepository.saveAndFlush(Exam.builder()
                .userId(authService.getCurrentUser().getId())
                .title("Untitled " + examRepository.countByUserId(authService.getCurrentUser().getId()).intValue())
                .image("")
                .build());

        return ExamResponse.builder()
                .id(exam.getId())
                .title(exam.getTitle())
                .image(exam.getImage())
                .createdAt(exam.getCreatedAt())
                .build();
    }

    public void updateExam(Long id, ExamRequestBody requestBody) {
        Exam exam = examRepository.findByIdAndUserId(id, authService.getCurrentUser().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found"));

        if (requestBody.getTitle() != null) exam.setTitle(exam.getTitle());
        if (requestBody.getImage() != null) exam.setImage(exam.getImage());

        examRepository.save(exam);
    }

    public void deleteExam(Long id) {
        Exam exam = examRepository.findByIdAndUserId(id, authService.getCurrentUser().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found"));

        examRepository.delete(exam);
    }
}
