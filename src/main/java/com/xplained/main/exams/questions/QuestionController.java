package com.xplained.main.exams.questions;

import com.xplained.main.dto.exams.question.IdAndIndexRequestBody;
import com.xplained.main.dto.exams.question.QuestionRequestBody;
import com.xplained.main.dto.exams.question.QuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;


    @GetMapping("/{examId}")
    public List<QuestionResponse> getAllQuestions(@PathVariable Long examId) {
        return questionService.getAllQuestions(examId);
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
    public void changeQuestionIndex(@RequestBody IdAndIndexRequestBody requestBody) {
        questionService.changeQuestionIndex(requestBody);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }
}
