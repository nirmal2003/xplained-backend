package com.xplained.main.courses.modules.lessons.progress;

import com.xplained.main.dto.courses.user.progress.LessonProgressRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses/modules/lessons/progress")
@RequiredArgsConstructor
public class LessonProgressController {
    private final LessonProgressService lessonProgressService;


    @PutMapping("/{resourcesId}")
    public void updateProgress(@PathVariable Long resourcesId, @RequestBody LessonProgressRequestBody requestBody) {
        lessonProgressService.updateProgress(resourcesId, requestBody);
    }
}
