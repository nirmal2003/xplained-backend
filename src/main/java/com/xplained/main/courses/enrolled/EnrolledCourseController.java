package com.xplained.main.courses.enrolled;

import com.xplained.main.dto.courses.enrolled.EnrolledCourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses/enroll")
@RequiredArgsConstructor
public class EnrolledCourseController {
    private final EnrolledCourseService enrolledCourseService;


    @GetMapping
    public List<EnrolledCourseResponse> getEnrolledCourses() {
        return enrolledCourseService.getEnrolledCourses();
    }

    @GetMapping("/{courseId}")
    public Boolean isCourseEnrolled(@PathVariable Long courseId) {
        return enrolledCourseService.isCourseEnrolled(courseId);
    }

    @PostMapping("/{courseId}")
    public void enrollCourse(@PathVariable Long courseId) {
        enrolledCourseService.enrollCourse(courseId);
    }
}
