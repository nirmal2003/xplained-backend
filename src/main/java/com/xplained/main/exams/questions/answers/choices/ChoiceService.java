package com.xplained.main.exams.questions.answers.choices;

import com.xplained.main.dto.exams.question.IdAndIndexRequestBody;
import com.xplained.main.dto.exams.question.answers.choices.ChoiceRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChoiceService {
    private final ChoiceRepository choiceRepository;


    private void checkPermission() {
    }

    public List<Choice> getChoices(Long questionId) {
        return choiceRepository.findAllByQuestionIdOrderByIndexAsc(questionId);
    }

    public Choice createChoice(Long questionId) {
        Choice choice = Choice.builder()
                .questionId(questionId)
                .text("")
                .index(choiceRepository.countByQuestionId(questionId).intValue())
                .isAnswer(false)
                .build();

        return choiceRepository.saveAndFlush(choice);
    }

    public void updateChoice(Long id, ChoiceRequestBody requestBody) {
        Choice choice = choiceRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "choice not found"));

        if (requestBody.getText() != null) choice.setText(requestBody.getText());
        if (requestBody.getIsAnswer() != null) choice.setIsAnswer(requestBody.getIsAnswer());

        choiceRepository.save(choice);
    }

    public void updateChoiceIndex(IdAndIndexRequestBody requestBody) {
        requestBody.getData().forEach(_data -> {
            Optional<Choice> choice = choiceRepository.findById(_data.getId());

            choice.ifPresent(_choice -> {
                _choice.setIndex(_data.getIndex());
                
                choiceRepository.save(_choice);
            });
        });
    }

    public void deleteChoice(Long id) {
        Choice choice = choiceRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "choice not found"));

        choiceRepository.delete(choice);
    }
}
