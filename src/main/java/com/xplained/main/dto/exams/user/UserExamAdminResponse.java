package com.xplained.main.dto.exams.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserExamAdminResponse {
    private String name;
    private LocalDateTime createdAt;
}
