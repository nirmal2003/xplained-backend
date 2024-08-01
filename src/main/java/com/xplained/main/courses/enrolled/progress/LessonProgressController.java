package com.xplained.main.courses.enrolled.progress;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses/users/progress")
@RequiredArgsConstructor
public class LessonProgressController {
    private final LessonProgressService lessonProgressService;


    @GetMapping("/{resourcesId}")
    public Boolean checkResourcesCompleted(@PathVariable Long resourcesId) {
        return lessonProgressService.checkResourcesCompleted(resourcesId);
    }

    @PostMapping("/{lessonId}/{resourcesId}")
    public void completeLesson(@PathVariable Long lessonId, @PathVariable Long resourcesId) {
        lessonProgressService.completeLesson(lessonId, resourcesId);
    }
}
