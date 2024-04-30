package com.xplained.main.dto.courses.modules;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class CourseModuleResponse {
    private Long id;
    private String name;

    private LocalDateTime createdAt;
}
