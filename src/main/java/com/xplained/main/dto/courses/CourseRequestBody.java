package com.xplained.main.dto.courses;

import lombok.Data;

import java.util.List;

@Data
public class CourseRequestBody {
    private String title;
    private String shortDescription;
    private String description;
    private String requirements;
    private String learners;
    private Boolean isPrivate;
    private String image;
    private String video;
}
