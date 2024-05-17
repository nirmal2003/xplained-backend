package com.xplained.main.courses.modules.lessons;

import com.xplained.main.dto.courses.modules.lessons.LessonRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses/modules/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    
    @GetMapping("/{moduleId}")
    public List<Lesson> getLessons(@PathVariable Long moduleId) {
        return lessonService.getLessons(moduleId);
    }

    @GetMapping("/details/{id}")
    public Lesson getLesson(@PathVariable Long id) {
        return lessonService.getLesson(id);
    }

    @GetMapping("/count/{moduleId}")
    public Long getNumberOfLessons(@PathVariable Long moduleId) {
        return lessonService.getNumberOfLessons(moduleId);
    }

    @PostMapping("/{moduleId}")
    public Lesson createLesson(@PathVariable Long moduleId) {
        return lessonService.createLesson(moduleId);
    }

    @PutMapping("/{id}")
    public void updateLessons(@PathVariable Long id, @RequestBody LessonRequestBody requestBody) {
        lessonService.updateLessons(id, requestBody);
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
    }
}
