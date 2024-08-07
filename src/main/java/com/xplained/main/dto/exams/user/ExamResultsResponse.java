package com.xplained.main.dto.exams.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamResultsResponse {
    private Integer correct;
    private Integer incorrect;
    private Integer correctMcq;
    private Integer correctText;
}
