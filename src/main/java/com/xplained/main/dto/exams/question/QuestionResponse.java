package com.xplained.main.dto.exams.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class QuestionResponse {
    private Long id;
    private String question;
    private Integer type;
    private LocalDateTime createdAt;
}
