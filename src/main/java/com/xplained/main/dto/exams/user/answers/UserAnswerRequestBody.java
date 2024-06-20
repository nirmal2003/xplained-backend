package com.xplained.main.dto.exams.user.answers;

import lombok.Data;

@Data
public class UserAnswerRequestBody {
    private Integer type;
    private Long mcqAnswer;
    private String textAnswer;
    private Boolean isCorrect;
}
