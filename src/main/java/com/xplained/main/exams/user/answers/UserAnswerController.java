package com.xplained.main.exams.user.answers;

import com.xplained.main.dto.exams.user.answers.UserAnswerRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exams/user/answers")
@RequiredArgsConstructor
public class UserAnswerController {
    private final UserAnswerService userAnswerService;


    @GetMapping("/{userExamId}/{questionId}")
    public UserAnswer getAnswerDetails(@PathVariable Long userExamId, @PathVariable Long questionId) {
        return userAnswerService.getAnswerDetails(userExamId, questionId);
    }

    @PutMapping("/{userExamId}/{questionId}")
    public void changeAnswer(@PathVariable Long userExamId, @PathVariable Long questionId, @RequestBody UserAnswerRequestBody requestBody) {
        userAnswerService.changeAnswer(userExamId, questionId, requestBody);
    }
}
