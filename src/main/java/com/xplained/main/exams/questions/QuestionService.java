package com.xplained.main.exams.questions;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.exams.question.*;
import com.xplained.main.exams.Exam;
import com.xplained.main.exams.ExamRepository;
import com.xplained.main.exams.user.UserExam;
import com.xplained.main.exams.user.UserExamRepository;
import com.xplained.main.exams.user.UserExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final ExamRepository examRepository;
    private final UserExamRepository userExamRepository;
    private final AuthService authService;
    private final UserExamService userExamService;


    private void checkPermission() {
    }

    public List<Long> getAllQuestions(Long examId) {

        System.out.println("Exam id " + examId);

        Exam exam = examRepository.findById(examId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found"));

        if (exam.getIsTextEnabled()) return questionRepository.findAllByExamId(examId);
        else return questionRepository.findAllMcqByExamId(examId);
    }

    public QuestionResponse getQuestionByIndex(Long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "question not found"));
        System.out.println(question);

        Optional<UserExam> userExam = userExamRepository.findByUserIdAndExamId(authService.getCurrentUser().getId(), question.getExamId());

//            userExamService.updateExamProgress(question.getExamId());

        userExam.ifPresent(value -> {
            value.setCurrentIndex(question.getIndex());

            userExamRepository.save(value);
        });

        return QuestionResponse.builder()
                .id(question.getId())
                .question(question.getQuestion())
                .type(question.getType())
                .index(question.getRealIndex())
                .createdAt(question.getCreatedAt())
                .build();


    }

    public List<Long> getAllQuestionUserSide(Long examId) {
        Exam exam = examRepository.findById(examId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found"));

        if (exam.getIsTextEnabled()) return questionRepository.findAllByExamId(examId);
        else return questionRepository.findAllMcqByExamId(examId);
    }

    public Long getQuestionCountUserSide(Long examId) {
        Exam exam = examRepository.findById(examId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found"));

        if (exam.getIsTextEnabled()) return questionRepository.countByExamId(examId);
        else return questionRepository.countByExamIdAndType(examId, 1);
    }

    public QuestionResponse createQuestion(Long examId) {
        if (!examRepository.existsById(examId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found");

        Question question = questionRepository.saveAndFlush(Question.builder()
                .examId(examId)
                .type(1)
                .question("")
                .index(questionRepository.countByExamId(examId).intValue())
                .realIndex(questionRepository.countByExamId(examId).intValue() + 1)
                .build());

        return QuestionResponse.builder()
                .id(question.getId())
                .question(question.getQuestion())
                .type(question.getType())
                .index(question.getRealIndex())
                .createdAt(question.getCreatedAt())
                .build();
    }

    public void updateQuestion(Long id, QuestionRequestBody requestBody) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "question not found"));

        if (requestBody.getQuestion() != null) question.setQuestion(requestBody.getQuestion());
        if (requestBody.getType() != null) question.setType(requestBody.getType());
        if (requestBody.getIndex() != null) question.setIndex(requestBody.getIndex());
        if (requestBody.getMarks() != null) question.setMarks(requestBody.getMarks());

        questionRepository.save(question);
    }

    public void changeQuestionIndex(List<IdAndIndex> requestBody) {
        requestBody.forEach(_data -> {
            Optional<Question> question = questionRepository.findById(((IdAndIndex) _data).getId());

            question.ifPresent(_question -> {
                _question.setIndex(_data.getIndex());

                questionRepository.save(_question);
            });
        });
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public List<QuestionAdminResponse> getAllQuestionsInAdmin(Long examId) {
        return questionRepository.getAllByExamId(examId);
    }
}
