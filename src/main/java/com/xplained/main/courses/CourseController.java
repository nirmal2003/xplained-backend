package com.xplained.main.courses;

import com.xplained.main.dto.courses.CourseRequestBody;
import com.xplained.main.dto.courses.CourseResponse;
import com.xplained.main.dto.courses.CourseSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;


    @GetMapping
    public List<CourseResponse> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/search")
    public List<CourseSearchResponse> searchCourses(@RequestParam(name = "title", required = true) String title) {
        return courseService.searchCourses(title);
    }

    @GetMapping("/{id}")
    public Course getCourseDetails(@PathVariable Long id) {
        return courseService.getCourseDetails(id);
    }

    @PostMapping
    public Course createCourse() {
        return courseService.createCourse();
    }

    @PutMapping("/{id}")
    public void updateCourse(@PathVariable Long id, @RequestBody CourseRequestBody requestBody) {
        courseService.updateCourse(id, requestBody);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}
