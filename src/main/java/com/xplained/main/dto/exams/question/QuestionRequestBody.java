package com.xplained.main.dto.exams.question;

import lombok.Data;

@Data
public class QuestionRequestBody {
    private String question;
    private Integer type;
    private Integer index;
    private Float marks;
}
