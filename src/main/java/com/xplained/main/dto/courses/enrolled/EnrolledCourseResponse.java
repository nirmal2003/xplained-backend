package com.xplained.main.dto.courses.enrolled;

import com.xplained.main.courses.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrolledCourseResponse {
    private Long id;
    private Long courseId;
    private Long userId;
    private String title;
    private String image;
    private String userName;
    private String userImage;
    private Integer numberOfLessons;
    private Integer completedLessons;
    private LocalDateTime createdAt;
}
