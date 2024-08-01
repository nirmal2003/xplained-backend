package com.xplained.main.courses.enrolled.progress.resources;

import com.xplained.main.auth.AuthService;
import com.xplained.main.courses.modules.lessons.resources.LessonResources;
import com.xplained.main.courses.modules.lessons.resources.LessonResourcesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CompletedResourcesService {
    private final CompletedResourcesRepository completedResourcesRepository;
    private final LessonResourcesRepository lessonResourcesRepository;
    private final AuthService authService;


    public void checkPermission() {}

    public void completeResources(Long resourcesId) {
        LessonResources resources = lessonResourcesRepository.findById(resourcesId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "resources not found"));

        CompletedResources completedResources = CompletedResources.builder()
                .userId(authService.getCurrentUser().getId())
                .resourcesId(resourcesId)
                .lessonId(resources.getLessonId())
                .build();

        completedResourcesRepository.save(completedResources);
    }
}
