package com.xplained.main.exams.user;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.exams.user.*;
import com.xplained.main.exams.Exam;
import com.xplained.main.exams.ExamRepository;
import com.xplained.main.exams.questions.QuestionRepository;
import com.xplained.main.exams.questions.answers.choices.Choice;
import com.xplained.main.exams.questions.answers.choices.ChoiceRepository;
import com.xplained.main.exams.user.answers.UserAnswer;
import com.xplained.main.exams.user.answers.UserAnswerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserExamService {
    private final UserExamRepository userExamRepository;
    private final AuthService authService;
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;
    private final ChoiceRepository choiceRepository;
    private final UserAnswerRepository userAnswerRepository;


    public List<UserExamResponse> getAllUserExam() {
        return userExamRepository.findAllByUserIdOrderByCreatedAtDesc(authService.getCurrentUser().getId());
    }

    public UserExam userExamDetails(Long examId) {
        if (!examRepository.existsById(examId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found");

        Optional<UserExam> userExam = userExamRepository.findByUserIdAndExamId(authService.getCurrentUser().getId(), examId);

        return userExam.orElseGet(() -> createUserExam(examId));
    }

    public UserExam createUserExam(Long examId) {
        Exam exam = examRepository.findById(examId).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found"));

        UserExam userExam = UserExam.builder()
                .examId(examId)
                .userId(authService.getCurrentUser().getId())
                .currentIndex(0)
                .duration(exam.getDuration())
                .build();

        return userExamRepository.saveAndFlush(userExam);
    }

    public void updateUserExam(Long examId, UserExamRequestBody requestBody) {
        UserExam exam = userExamRepository.findByUserIdAndExamId(authService.getCurrentUser().getId(), examId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found"));

        if (requestBody.getDuration() != null) exam.setDuration(requestBody.getDuration());
        if (requestBody.getCurrentIndex() != null) exam.setCurrentIndex(requestBody.getCurrentIndex());

        userExamRepository.save(exam);
    }

    public void deleteUserExam(Long examId) {
        UserExam userExam = userExamRepository.findByUserIdAndExamId(authService.getCurrentUser().getId(), examId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found"));

        userExamRepository.delete(userExam);
    }

    public UserResults getExamResults(Long examId) {

        UserResults results = UserResults.builder()
                .correct(0)
                .incorrect(0)
                .build();

        if (!userExamRepository.existsByUserIdAndExamId(authService.getCurrentUser().getId(), examId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found");

        List<Long> questions = questionRepository.findAllIds(examId, 1);

        questions.forEach(questionId -> {

            Pageable pageable = PageRequest.of(1, 1);

            Optional<UserAnswer> userAnswer = userAnswerRepository.findUserAnswer(questionId);



            if (userAnswer.isPresent()) {
                results.setCorrect(results.getCorrect() + 1);
            } else {
                results.setIncorrect(results.getIncorrect() + 1);
            }
        });

        return results;
    }

    public void updateNextIndex(Long examId) {
        UserExam userExam = userExamRepository.findByUserIdAndExamId(authService.getCurrentUser().getId(), examId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found"));

        userExam.setCurrentIndex(userExam.getCurrentIndex());

        userExamRepository.save(userExam);
    }

    public List<Long> unAnsweredQuestions(Long examId) {

        if (!userExamRepository.existsByUserIdAndExamId(authService.getCurrentUser().getId(), examId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found");

        List<Long> questions = questionRepository.findAllByExamId(examId);

        List<Long> unanswered = new ArrayList<>();

        questions.forEach(id -> {
            if (!userAnswerRepository.existsByQuestionId(id)) unanswered.add(id);
        });

        return unanswered;
    }

    public List<UserExamAdminResponse> getAllExamsInAdmin(Long examId) {
        return userExamRepository.getAllExamsByAdmin(examId);
    }

    public ExamResultsResponse getUserExamResults(Long examId) {
        UserExam userExam = userExamRepository.findByUserIdAndExamId(authService.getCurrentUser().getId(), examId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found"));

        return ExamResultsResponse.builder()
                .correct(userAnswerRepository.countByUserExamIdAndIsCorrect(userExam.getId(), true).intValue())
                .incorrect(userAnswerRepository.countByUserExamIdAndIsCorrect(userExam.getId(), false).intValue())
                .correctMcq(userAnswerRepository.countByUserExamIdAndIsCorrectAndType(userExam.getId(), true, 1).intValue())
                .correctText(userAnswerRepository.countByUserExamIdAndIsCorrectAndType(userExam.getId(), true, 2).intValue())
                .build();
    }

    public void updateExamProgress(Long examId) {
        UserExam userExam = userExamRepository.findByUserIdAndExamId(authService.getCurrentUser().getId(), examId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "exam not found"));

        Long questions = questionRepository.countByExamId(examId);
        Long answers = userAnswerRepository.countByUserExamId(userExam.getId());

        userExam.setProgress((answers.floatValue() / questions.floatValue()) * 100);

        userExamRepository.save(userExam);
    }
}
