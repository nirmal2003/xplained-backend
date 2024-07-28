package com.xplained.main.dto.courses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class CourseDetailsResponse {
    private Long id;
    private String title;
    private String heading;
    private String shortDescription;
    private String description;
    private String image;
    private String video;
    private Boolean isPrivate;
    private Boolean isPublished;
    private Boolean isActive;
    private Long numberOfLessons;
    private Long numberOfModels;

    private CourseDetailsResponseUser user;

    private LocalDateTime createdAt;
}
