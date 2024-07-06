package com.xplained.main.courses.modules.lessons.progress;

import com.xplained.main.courses.modules.lessons.resources.LessonResources;
import com.xplained.main.courses.modules.lessons.resources.LessonResourcesRepository;
import com.xplained.main.dto.courses.user.progress.LessonProgressRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonProgressService {
    private final LessonProgressRepository lessonProgressRepository;
    private final LessonResourcesRepository lessonResourcesRepository;


    public void updateProgress(Long resourcesId, LessonProgressRequestBody requestBody) {
        LessonResources resources = lessonResourcesRepository.findById(resourcesId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "resources not found"));

        Optional<LessonProgress> progress = lessonProgressRepository.findByResourceId(resourcesId);

        if (progress.isEmpty()) {
            lessonProgressRepository.save(LessonProgress.builder()
                    .lessonId(resources.getLessonId())
                    .resourceId(resourcesId)
                    .percentage(requestBody.getPercentage())
                    .build());
        } else {
            progress.get().setPercentage(requestBody.getPercentage());
            lessonProgressRepository.save(progress.get());
        }
    }
}
