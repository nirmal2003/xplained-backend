package com.xplained.main.dto.courses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private Long id;
    private String title;
    private String image;
    private Boolean isPrivate;
    private Boolean isPublished;
    private Boolean isActive;
}
