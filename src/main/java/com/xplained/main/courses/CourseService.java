package com.xplained.main.courses;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.courses.CourseRequestBody;
import com.xplained.main.dto.courses.CourseResponse;
import com.xplained.main.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final AuthService authService;
    private final CourseRepository courseRepository;


    public List<CourseResponse> getAllCourses() {
        UserDTO user = authService.getCurrentUser();

        return courseRepository.findAllByUserId(user.getId());
    }

    public Course getCourseDetails(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "course not found"));
    }

    public Course createCourse() {
        UserDTO user = authService.getCurrentUser();

        Course course = Course.builder()
                .title("Untitled " + courseRepository.countByUserId(user.getId()))
                .userId(user.getId())
                .isPrivate(false)
                .isPublished(false)
                .isActive(false)
                .build();

        return courseRepository.saveAndFlush(course);
    }

    public void updateCourse(Long id, CourseRequestBody requestBody) {
        UserDTO user = authService.getCurrentUser();

        Course course = courseRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "course not found"));

        if (requestBody.getTitle() != null) course.setTitle(requestBody.getTitle());
        if (requestBody.getShortDescription() != null) course.setShortDescription(requestBody.getShortDescription());
        if (requestBody.getDescription() != null) course.setDescription(requestBody.getDescription());
        if (requestBody.getRequirements() != null) course.setRequirements(requestBody.getRequirements());
        if (requestBody.getLearners() != null) course.setLearners(requestBody.getLearners());
        if (requestBody.getIsPrivate() != null) course.setIsPrivate(requestBody.getIsPrivate());

        courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        UserDTO user = authService.getCurrentUser();

        Course course = courseRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "course not found"));

        courseRepository.delete(course);
    }
}
