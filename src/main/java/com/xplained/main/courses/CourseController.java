package com.xplained.main.courses;

import com.xplained.main.dto.courses.CourseDetailsResponse;
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
    public List<CourseResponse> getAllCourses(@RequestParam(name = "page", defaultValue = "0", required = false) Integer page) {
        return courseService.getAllCourses(page);
    }

    @GetMapping("/search")
    public List<CourseSearchResponse> searchCourses(@RequestParam(name = "title", required = false, defaultValue = "") String title, @RequestParam(name = "page", defaultValue = "0", required = false) Integer page) {
        return courseService.searchCourses(title, page);
    }

    @GetMapping("/{id}")
    public CourseDetailsResponse getCourseDetails(@PathVariable Long id) {
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
