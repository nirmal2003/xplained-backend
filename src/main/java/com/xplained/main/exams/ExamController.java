package com.xplained.main.exams;

import com.xplained.main.dto.exams.ExamRequestBody;
import com.xplained.main.dto.exams.ExamResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;


    @GetMapping
    public List<ExamResponse> getAllExamsByCreator() {
        return examService.getAllExamsByCreator();
    }

    @GetMapping("/{id}")
    public ExamResponse getExamDetails(@PathVariable Long id) {
        return examService.getExamDetails(id);
    }

    @PostMapping
    public ExamResponse createExam() {
        return examService.createExam();
    }


    @PutMapping("/{id}")
    public void updateExam(@PathVariable Long id, @RequestBody ExamRequestBody requestBody) {
        examService.updateExam(id, requestBody);
    }

    @DeleteMapping("/{id}")
    public void deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
    }
}
