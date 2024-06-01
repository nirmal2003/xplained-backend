package com.xplained.main.exams.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams/user")
@RequiredArgsConstructor
public class UserExamController {
    private final UserExamService userExamService;


    @GetMapping
    public List<UserExam> getAllUserExam() {
        return userExamService.getAllUserExam();
    }

    @GetMapping("/{examId}")
    public UserExam userExamDetails(@PathVariable Long examId) {
        return userExamService.userExamDetails(examId);
    }

    @PostMapping("/{examId}")
    public UserExam createUserExam(@PathVariable Long examId) {
        return userExamService.createUserExam(examId);
    }

    @DeleteMapping("/{examId}")
    public void deleteUserExam(@PathVariable Long examId) {
        userExamService.deleteUserExam(examId);
    }
}
