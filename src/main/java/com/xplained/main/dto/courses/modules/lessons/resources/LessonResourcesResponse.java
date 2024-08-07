package com.xplained.main.dto.courses.modules.lessons.resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class LessonResourcesResponse {
    private Long id;
    private Integer type;
    private String name;
    private Float progress;
    private Boolean isCompleted;

    public LessonResourcesResponse(Long id, Integer type, String name, String progress, Boolean isCompleted, LocalDateTime createdAt) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.progress = Float.parseFloat(progress);
        this.isCompleted = isCompleted;
        this.createdAt = createdAt;
    }

    private LocalDateTime createdAt;
}
