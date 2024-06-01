package com.xplained.main.exams.questions;

import com.xplained.main.dto.exams.question.IdAndIndex;
import com.xplained.main.dto.exams.question.IdAndIndexRequestBody;
import com.xplained.main.dto.exams.question.QuestionRequestBody;
import com.xplained.main.dto.exams.question.QuestionResponse;
import com.xplained.main.exams.ExamRepository;
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


    private void checkPermission() {
    }

    public List<QuestionResponse> getAllQuestions(Long examId) {
        return questionRepository.findAllByExamId(examId);
    }

    public QuestionResponse createQuestion(Long examId) {
        if (!examRepository.existsById(examId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found");

        Question question = questionRepository.saveAndFlush(Question.builder()
                .examId(examId)
                .type(1)
                .question("")
                .index(questionRepository.countByExamId(examId).intValue())
                .build());

        return QuestionResponse.builder()
                .id(question.getId())
                .question(question.getQuestion())
                .type(question.getType())
                .createdAt(question.getCreatedAt())
                .build();
    }

    public void updateQuestion(Long id, QuestionRequestBody requestBody) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "question not found"));

        if (requestBody.getQuestion() != null) question.setQuestion(requestBody.getQuestion());
        if (requestBody.getType() != null) question.setType(requestBody.getType());
        if (requestBody.getIndex() != null) question.setIndex(requestBody.getIndex());

        questionRepository.save(question);
    }

    public void changeQuestionIndex(IdAndIndexRequestBody requestBody) {
        requestBody.getData().forEach(_data -> {
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
}
