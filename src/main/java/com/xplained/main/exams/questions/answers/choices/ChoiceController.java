package com.xplained.main.exams.questions.answers.choices;

import com.xplained.main.dto.exams.question.IdAndIndexRequestBody;
import com.xplained.main.dto.exams.question.answers.choices.ChoiceRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams/questions/answers/mcq")
@RequiredArgsConstructor
public class ChoiceController {
    private final ChoiceService choiceService;


    @GetMapping("/{questionId}")
    public List<Choice> getChoices(@PathVariable Long questionId) {
        return choiceService.getChoices(questionId);
    }

    @PostMapping("/{questionId}")
    public Choice createChoice(@PathVariable Long questionId) {
        return choiceService.createChoice(questionId);
    }

    @PutMapping("/{id}")
    public void updateChoice(@PathVariable Long id, @RequestBody ChoiceRequestBody requestBody) {
        choiceService.updateChoice(id, requestBody);
    }

    @PutMapping("/index")
    public void updateChoiceIndex(@RequestBody IdAndIndexRequestBody requestBody) {
        choiceService.updateChoiceIndex(requestBody);
    }

    @DeleteMapping("/{id}")
    public void deleteChoice(@PathVariable Long id) {
        choiceService.deleteChoice(id);
    }
}
