package com.xplained.main.dto.courses.modules.lessons.resources;

import lombok.Data;

@Data
public class LessonResourcesRequestBody {
    private String name;
    private Integer type;
    private String videoUrl;
    private Long sliderId;
}
