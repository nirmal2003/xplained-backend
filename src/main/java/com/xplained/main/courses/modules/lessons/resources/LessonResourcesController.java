package com.xplained.main.courses.modules.lessons.resources;

import com.xplained.main.dto.courses.modules.lessons.resources.LessonResourcesRequestBody;
import com.xplained.main.dto.courses.modules.lessons.resources.LessonResourcesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses/modules/lessons/resources")
@RequiredArgsConstructor
public class LessonResourcesController {
    private final LessonResourcesService lessonResourcesService;


    @GetMapping("/{lessonId}")
    public List<LessonResourcesResponse> getAllResources(@PathVariable Long lessonId) {
        return lessonResourcesService.getAllResources(lessonId);
    }

    @GetMapping("/details/{resourcesId}")
    public LessonResources getResources(@PathVariable Long resourcesId) {
        return lessonResourcesService.getResources(resourcesId);
    }

    @PostMapping("/{lessonId}")
    public LessonResources createResources(@PathVariable Long lessonId, @RequestBody LessonResourcesRequestBody requestBody) {
        return lessonResourcesService.createResources(lessonId, requestBody);
    }

    @PutMapping("/{id}")
    public void updateResources(@PathVariable Long id, @RequestBody LessonResourcesRequestBody requestBody) {
        lessonResourcesService.updateResources(id, requestBody);
    }

    @DeleteMapping("/{id}")
    public void deleteResources(@PathVariable Long id) {
        lessonResourcesService.deleteResources(id);
    }
}
