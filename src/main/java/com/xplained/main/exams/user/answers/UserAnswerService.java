package com.xplained.main.exams.user.answers;

import com.xplained.main.dto.exams.user.answers.UserAnswerRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAnswerService {
    private final UserAnswerRepository userAnswerRepository;


    private void checkPermissions() {}

    public UserAnswer getAnswerDetails(Long userExamId, Long questionId) {
        return userAnswerRepository.findByUserExamIdAndQuestionId(userExamId, questionId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "answer not found"));
    }

    public UserAnswer getAnswerDetailsInAdmin(Long questionId) {
        return userAnswerRepository.findByQuestionId(questionId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "answer not found"));
    }

    public void changeAnswer(Long userExamId, Long questionId, UserAnswerRequestBody requestBody) {
        Optional<UserAnswer> answer = userAnswerRepository.findByUserExamIdAndQuestionId(userExamId, questionId);

        UserAnswer _answer = null;

        _answer = answer.orElseGet(() -> userAnswerRepository.saveAndFlush(UserAnswer.builder()
                .userExamId(userExamId)
                .questionId(questionId)
                .type(requestBody.getType())
                .isReviewed(false)
                .build()));

//        if (answer.isPresent()) {
//            _answer = answer.get();
//        } else {
//            _answer = userAnswerRepository.saveAndFlush(UserAnswer.builder()
//                    .userExamId(userExamId)
//                    .questionId(questionId)
//                    .type(requestBody.getType())
//                    .build());
//        }

        if (_answer.getType() == 1) _answer.setMcqAnswer(requestBody.getMcqAnswer());
        else if (_answer.getType() == 2) _answer.setTextAnswer(requestBody.getTextAnswer());

        userAnswerRepository.save(_answer);
    }

    public void reviewAnswer(Long id, UserAnswerRequestBody requestBody) {
        UserAnswer answer = userAnswerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "answer not found"));

        if (!answer.getIsReviewed()) {
            answer.setIsReviewed(true);
        }

        answer.setIsCorrect(requestBody.getIsCorrect());

        userAnswerRepository.save(answer);
    }
}
