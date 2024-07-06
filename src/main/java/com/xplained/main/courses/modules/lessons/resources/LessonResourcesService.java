package com.xplained.main.courses.modules.lessons.resources;

import com.xplained.main.auth.AuthService;
import com.xplained.main.courses.CourseRepository;
import com.xplained.main.courses.enrolled.EnrolledCourseRepository;
import com.xplained.main.courses.modules.CourseModule;
import com.xplained.main.courses.modules.CourseModuleRepository;
import com.xplained.main.courses.modules.lessons.Lesson;
import com.xplained.main.courses.modules.lessons.LessonRepository;
import com.xplained.main.dto.courses.modules.lessons.resources.LessonResourcesRequestBody;
import com.xplained.main.dto.courses.modules.lessons.resources.LessonResourcesResponse;
import com.xplained.main.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonResourcesService {
    private final LessonResourcesRepository lessonResourcesRepository;
    private final LessonRepository lessonRepository;
    private final CourseModuleRepository moduleRepository;
    private final CourseRepository courseRepository;
    private final AuthService authService;
    private final EnrolledCourseRepository enrolledCourseRepository;


    private void checkCourseCreator(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "lesson not found"));

        CourseModule module = moduleRepository.findById(lesson.getModuleId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "module not found"));

        UserDTO user = authService.getCurrentUser();

        if (!courseRepository.existsByIdAndUserId(module.getCourseId(), user.getId())) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "course not found");
    }

    public List<LessonResourcesResponse> getAllResources(Long lessonId) {


        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "lesson not found"));

        CourseModule module = moduleRepository.findById(lesson.getModuleId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "module not found"));

        UserDTO user = authService.getCurrentUser();


        if (enrolledCourseRepository.existsByCourseIdAndUserId(module.getCourseId(), user.getId()) || courseRepository.existsByIdAndUserId(module.getCourseId(), user.getId())) {

            return lessonResourcesRepository.findAllByLessonIdOrderByCreatedAtAsc(lessonId, authService.getCurrentUser().getId());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "resources not found");
    }

    public LessonResources getResources(Long resourcesId) {
        LessonResources resources = lessonResourcesRepository.findById(resourcesId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "resources not found"));


        Lesson lesson = lessonRepository.findById(resources.getLessonId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "lesson not found"));

        CourseModule module = moduleRepository.findById(lesson.getModuleId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "module not found"));

        UserDTO user = authService.getCurrentUser();


        if (enrolledCourseRepository.existsByCourseIdAndUserId(module.getCourseId(), user.getId()) || courseRepository.existsByIdAndUserId(module.getCourseId(), user.getId())) {
            return resources;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "resources not found");
    }

    public LessonResources createResources(Long lessonId, LessonResourcesRequestBody requestBody) {
        checkCourseCreator(lessonId);

        LessonResources resources = LessonResources.builder()
                .lessonId(lessonId)
                .type(requestBody.getType())
                .name("Untitled " + lessonResourcesRepository.countByLessonId(lessonId))
                .sliderId(requestBody.getSliderId())
                .video(requestBody.getVideo())
                .examId(requestBody.getExamId())
                .build();

//        if (requestBody.getType() == 1) {
//        } else if (requestBody.getType() == 2) {
//
//        }

        return lessonResourcesRepository.saveAndFlush(resources);
    }

    public void updateResources(Long id, LessonResourcesRequestBody requestBody) {
        LessonResources resources = lessonResourcesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "resources not found"));

        System.out.println(resources);

        checkCourseCreator(resources.getLessonId());

        System.out.println(requestBody);

        if (requestBody.getName() != null) resources.setName(requestBody.getName());

        lessonResourcesRepository.save(resources);
    }

    public void deleteResources(Long id) {
        LessonResources resources = lessonResourcesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "resources not found"));

        checkCourseCreator(resources.getLessonId());

        lessonResourcesRepository.delete(resources);
    }
}
