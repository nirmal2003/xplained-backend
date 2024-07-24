package com.xplained.main.dto.courses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CourseDetailsResponseUser {
    private Long id;
    private String name;
    private String heading;
    private String image;
}
