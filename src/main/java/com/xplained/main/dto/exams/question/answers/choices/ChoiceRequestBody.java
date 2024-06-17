package com.xplained.main.dto.exams.question.answers.choices;

import lombok.Data;

@Data
public class ChoiceRequestBody {
    private String text;
    private Boolean isAnswer;
}
