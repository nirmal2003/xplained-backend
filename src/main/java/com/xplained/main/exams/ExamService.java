package com.xplained.main.exams;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.exams.ExamRequestBody;
import com.xplained.main.dto.exams.ExamResponse;
import com.xplained.main.exams.questions.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ExamService {
    private final ExamRepository examRepository;
    private final AuthService authService;
    private final QuestionRepository questionRepository;


    public List<ExamResponse> getAllExamsByCreator() {
        return examRepository.findAllByUserIdOrderByCreatedAtDesc(authService.getCurrentUser().getId());
    }

    public ExamResponse getExamDetails(Long id) {
        Exam exam = examRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found"));

        return ExamResponse.builder()
                .id(exam.getId())
                .title(exam.getTitle())
                .image(exam.getImage())
                .isTextEnabled(exam.getIsTextEnabled())
                .duration(exam.getDuration())
                .isPublished(exam.getIsPublished())
                .createdAt(exam.getCreatedAt())
                .build();
    }

    public ExamResponse createExam() {
        Exam exam = examRepository.saveAndFlush(Exam.builder()
                .userId(authService.getCurrentUser().getId())
                .title("Untitled " + examRepository.countByUserId(authService.getCurrentUser().getId()).intValue())
                .image("")
                .isTextEnabled(false)
                .duration(BigInteger.valueOf(60 * 60 * 1000))
                .isPublished(false)
                .build());

        return ExamResponse.builder()
                .id(exam.getId())
                .title(exam.getTitle())
                .image(exam.getImage())
                .isPublished(exam.getIsPublished())
                .createdAt(exam.getCreatedAt())
                .build();
    }

    public void updateExam(Long id, ExamRequestBody requestBody) {
        Exam exam = examRepository.findByIdAndUserId(id, authService.getCurrentUser().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found"));

        if (requestBody.getTitle() != null) exam.setTitle(requestBody.getTitle());
        if (requestBody.getImage() != null) exam.setImage(requestBody.getImage());
        if (requestBody.getIsTextEnabled() != null) exam.setIsTextEnabled(requestBody.getIsTextEnabled());
        if (requestBody.getDuration() != null) exam.setDuration(requestBody.getDuration());
        if (requestBody.getIsPublished() != null) {
            if (requestBody.getIsPublished() && !exam.getIsTextEnabled()) {
                questionRepository.deleteByExamIdAndType(exam.getId(), 2);
            }
            exam.setIsPublished(requestBody.getIsPublished());
        }

        examRepository.save(exam);
    }

    public void deleteExam(Long id) {
        Exam exam = examRepository.findByIdAndUserId(id, authService.getCurrentUser().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found"));

        examRepository.delete(exam);
    }
}
