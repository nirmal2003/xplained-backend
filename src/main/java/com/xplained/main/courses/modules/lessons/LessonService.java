package com.xplained.main.courses.modules.lessons;

import com.xplained.main.auth.AuthService;
import com.xplained.main.courses.CourseRepository;
import com.xplained.main.courses.modules.CourseModule;
import com.xplained.main.courses.modules.CourseModuleRepository;
import com.xplained.main.dto.courses.modules.lessons.LessonRequestBody;
import com.xplained.main.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final CourseModuleRepository moduleRepository;
    private final CourseRepository courseRepository;
    private final AuthService authService;

    public void checkModuleCreator(Long moduleId) {
        CourseModule module = moduleRepository.findById(moduleId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "course module not found"));

        UserDTO user = authService.getCurrentUser();

        if (!courseRepository.existsByIdAndUserId(module.getCourseId(), user.getId())) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "course not found");
    }

    public List<Lesson> getLessons(Long moduleId) {
        return lessonRepository.findAllByModuleId(moduleId);
    }

    public Lesson getLesson(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "lesson not found"));

//        checkModuleCreator(lesson.getModuleId());

        return lesson;
    }

    public Lesson createLesson(Long moduleId) {
        checkModuleCreator(moduleId);

        Lesson lesson = Lesson.builder()
                .name("lesson " + lessonRepository.countByModuleId(moduleId))
                .moduleId(moduleId)
                .index(lessonRepository.countByModuleId(moduleId).intValue())
                .build();

        return lessonRepository.saveAndFlush(lesson);
    }

    public void updateLessons(Long id, LessonRequestBody requestBody) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "lesson not found"));

        checkModuleCreator(lesson.getModuleId());

        if (requestBody.getName() != null) lesson.setName(requestBody.getName());

        lessonRepository.save(lesson);
    }

    public void deleteLesson(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "lesson not found"));

        checkModuleCreator(lesson.getModuleId());

        lessonRepository.delete(lesson);
    }
}
