package com.xplained.main.courses.enrolled.progress;

import com.xplained.main.auth.AuthService;
import com.xplained.main.courses.enrolled.progress.resources.CompletedResources;
import com.xplained.main.courses.enrolled.progress.resources.CompletedResourcesRepository;
import com.xplained.main.courses.enrolled.progress.resources.CompletedResourcesService;
import com.xplained.main.courses.modules.lessons.resources.LessonResourcesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonProgressService {
    private final LessonProgressRepository lessonProgressRepository;
    private final LessonResourcesRepository lessonResourcesRepository;
    private final CompletedResourcesRepository completedResourcesRepository;
    private final AuthService authService;
    private final CompletedResourcesService completedResourcesService;


    public Boolean checkResourcesCompleted(Long resourcesId) {
        return completedResourcesRepository.existsByResourcesId(resourcesId);
    }

    public void completeLesson(Long lessonId, Long resourcesId) {

        LessonProgress progress = null;

        progress = lessonProgressRepository.findById(lessonId).orElseGet(() -> lessonProgressRepository.saveAndFlush(LessonProgress.builder()
                .userId(authService.getCurrentUser().getId())
                .lessonId(lessonId)
                .percentage(0f)
                .build()));

        Long numberOfResources = lessonResourcesRepository.countByLessonId(lessonId);
        Long completedResources = completedResourcesRepository.countByLessonId(lessonId);


        if (!completedResourcesRepository.existsByResourcesId(resourcesId)) {
            completedResources += 1;
            completedResourcesService.completeResources(resourcesId);
        }

        Float percentage = (float) ((completedResources / numberOfResources) * 100);

        progress.setPercentage(percentage);

        lessonProgressRepository.saveAndFlush(progress);
    }
}
