package com.xplained.main.courses.enrolled;

import com.xplained.main.auth.AuthService;
import com.xplained.main.courses.CourseRepository;
import com.xplained.main.dto.courses.enrolled.EnrolledCourseResponse;
import com.xplained.main.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrolledCourseService {
    private final EnrolledCourseRepository enrolledCourseRepository;
    private final CourseRepository courseRepository;
    private final AuthService authService;



    public Boolean isCourseEnrolled(Long courseId) {
        return enrolledCourseRepository.existsByCourseIdAndUserId(courseId, authService.getCurrentUser().getId());
    }

    public List<EnrolledCourseResponse> getEnrolledCourses() {
        return enrolledCourseRepository.findAllByUserId(authService.getCurrentUser().getId());
    }

    public void enrollCourse(Long courseId) {

        UserDTO user = authService.getCurrentUser();

        if (!courseRepository.existsById(courseId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "course not found");

        if (enrolledCourseRepository.existsByCourseIdAndUserId(courseId, user.getId())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "already enrolled");

        enrolledCourseRepository.save(EnrolledCourse.builder()
                .userId(user.getId())
                .courseId(courseId)
                .build());
    }
}
