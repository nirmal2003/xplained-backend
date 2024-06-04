package com.xplained.main.dto.exams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamResponse {
    private Long id;
    private String title;
    private String image;
    private Boolean isTextEnabled;
    private Float duration;
    private LocalDateTime createdAt;
}
