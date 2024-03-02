package com.xplained.main.dto.courses;

import lombok.Data;

import java.util.List;

@Data
public class CourseRequestBody {
    private String title;
    private String heading;
    private String description;
    private List<Long> instructors;
    private List<String> points;
}
