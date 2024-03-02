package com.xplained.main.courses;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final AuthService authService;
    private final CourseRepository courseRepository;

//    public Course createCourse() {
//        UserDTO user = authService.getCurrentUser();
//
//        Course course = Course.builder()
//                .build();
//    }
}
