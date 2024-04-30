package com.xplained.main.courses.modules.lessons;

import com.xplained.main.dto.courses.modules.lessons.LessonRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cources/modules/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    
    @GetMapping("/{moduleId}")
    public List<Lesson> getLessons(@PathVariable Long moduleId) {
        return lessonService.getLessons(moduleId);
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
