package com.xplained.main.courses.enrolled;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses/enroll")
@RequiredArgsConstructor
public class EnrolledCourseController {
    private final EnrolledCourseService enrolledCourseService;


    @GetMapping("/{courseId}")
    public Boolean isCourseEnrolled(@PathVariable Long courseId) {
        return enrolledCourseService.isCourseEnrolled(courseId);
    }

    @PostMapping("/{courseId}")
    public void enrollCourse(@PathVariable Long courseId) {
        enrolledCourseService.enrollCourse(courseId);
    }
}
