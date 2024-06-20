package com.xplained.main.exams.user;

import com.xplained.main.dto.exams.user.UserExamAdminResponse;
import com.xplained.main.dto.exams.user.UserExamRequestBody;
import com.xplained.main.dto.exams.user.UserExamResponse;
import com.xplained.main.dto.exams.user.UserResults;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams/user")
@RequiredArgsConstructor
public class UserExamController {
    private final UserExamService userExamService;


    @GetMapping
    public List<UserExamResponse> getAllUserExam() {
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

    @PutMapping("/{examId}")
    public void updateUserExam(@PathVariable Long examId, @RequestBody UserExamRequestBody requestBody) {
        userExamService.updateUserExam(examId, requestBody);
    }

    @DeleteMapping("/{examId}")
    public void deleteUserExam(@PathVariable Long examId) {
        userExamService.deleteUserExam(examId);
    }

    @GetMapping("/results/{examId}")
    public UserResults getExamResults(@PathVariable Long examId) {
        return userExamService.getExamResults(examId);
    }

    @PutMapping("/index/{examId}")
    public void updateNextIndex(@PathVariable Long examId) {
        userExamService.updateNextIndex(examId);
    }

    @GetMapping("/status/{examId}")
    public List<Long> isExamCompleted(@PathVariable Long examId) {
        return userExamService.unAnsweredQuestions(examId);
    }

    @GetMapping("/admin/{examId}")
    public List<UserExamAdminResponse> getAllExamsInAdmin(@PathVariable Long examId) {
        return userExamService.getAllExamsInAdmin(examId);
    }
}
