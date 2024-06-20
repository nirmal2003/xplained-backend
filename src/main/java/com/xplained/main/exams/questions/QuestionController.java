package com.xplained.main.exams.questions;

import com.xplained.main.dto.exams.question.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;


    @GetMapping("/{examId}")
    public List<Long> getAllQuestions(@PathVariable Long examId) {
        return questionService.getAllQuestions(examId);
    }

    @GetMapping("/details/{questionId}")
    public QuestionResponse getQuestionByIndex(@PathVariable Long questionId) {
        return questionService.getQuestionByIndex(questionId);
    }

    @GetMapping("/user/{examId}")
    public List<Long> getAllQuestionUserSide(@PathVariable Long examId) {
        return questionService.getAllQuestionUserSide(examId);
    }

    @GetMapping("/user/count/{examId}")
    public Long getQuestionCountUserSide(@PathVariable Long examId) {
        return questionService.getQuestionCountUserSide(examId);
    }

    @PostMapping("/{examId}")
    public QuestionResponse createQuestion(@PathVariable Long examId) {
        return questionService.createQuestion(examId);
    }

    @PutMapping("/{id}")
    public void updateQuestion(@PathVariable Long id, @RequestBody QuestionRequestBody requestBody) {
        questionService.updateQuestion(id, requestBody);
    }

    @PutMapping("/index")
    public void changeQuestionIndex(@RequestBody List<IdAndIndex> requestBody) {
        questionService.changeQuestionIndex(requestBody);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }

    @GetMapping("/admin/{examId}")
    public List<QuestionAdminResponse> getAllQuestionsInAdmin(@PathVariable Long examId) {
        return questionService.getAllQuestionsInAdmin(examId);
    }
}
