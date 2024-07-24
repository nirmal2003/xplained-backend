package com.xplained.main.courses.modules;

import com.xplained.main.auth.AuthService;
import com.xplained.main.courses.CourseRepository;
import com.xplained.main.dto.courses.modules.CourseModuleRequestBody;
import com.xplained.main.dto.courses.modules.CourseModuleResponse;
import com.xplained.main.dto.courses.modules.IndexRequestBody;
import com.xplained.main.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseModuleService {
    private final CourseModuleRepository courseModuleRepository;
    private final CourseRepository courseRepository;
    private final AuthService authService;

    public void checkCourseCreator(Long courseId) {
        UserDTO user = authService.getCurrentUser();

        if (!courseRepository.existsByIdAndUserId(courseId, user.getId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "course not found");
    }

    public List<CourseModuleResponse> getAllModules(Long courseId) {

        if (!courseRepository.existsById(courseId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "course not found");

        Pageable pageable = PageRequest.of(0, 20);
        return courseModuleRepository.findAllByCourseId(courseId, pageable);
    }

    public CourseModuleResponse createModule(Long courseId) {

        checkCourseCreator(courseId);

        CourseModule module = courseModuleRepository.saveAndFlush(CourseModule.builder()
                .courseId(courseId)
                .name("Module " + courseModuleRepository.countByCourseId(courseId))
                .index(courseModuleRepository.countByCourseId(courseId).intValue())
                .build());

        return CourseModuleResponse.builder()
                .id(module.getId())
                .name(module.getName())
                .createdAt(module.getCreatedAt())
                .build();
    }

    public void updateModule(Long id, CourseModuleRequestBody requestBody) {
        CourseModule module = courseModuleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "course module not found"));

        checkCourseCreator(module.getCourseId());

        if (requestBody.getName() != null) module.setName(requestBody.getName());

        courseModuleRepository.save(module);
    }


    // background task
    public void updateIndex(List<IndexRequestBody> requestBody) {
        requestBody.forEach(value -> {

            courseModuleRepository.findById(value.getId()).ifPresent(module -> {
                module.setIndex(value.getIndex());

                courseModuleRepository.save(module);
            });

        });
    }

    public void deleteModule(Long id) {
        CourseModule module = courseModuleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "course module not found"));

        checkCourseCreator(module.getCourseId());

        courseModuleRepository.delete(module);
    }
}
