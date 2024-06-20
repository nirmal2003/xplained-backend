package com.xplained.main.dto.exams.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserExamResponse {
    private Long id;
    private Long examId;
    private String examTitle;
    private Boolean isCompleted;
    private Boolean isPending;
    private BigInteger duration;
    private LocalDateTime createdAt;
}
