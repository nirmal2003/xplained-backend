package com.xplained.main.models.objects.animations.controllers;

import com.xplained.main.dto.models.objects.animations.controller.AnimationControllerRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editor/animations/controllers")
@RequiredArgsConstructor
public class AnimationControllersController {
    private final AnimationControllerService animationControllerService;


    @GetMapping("/model/{id}")
    public List<AnimationController> getAnimationControllersByModel(@PathVariable Long id) {
        return animationControllerService.getAnimationControllersByModel(id);
    }

    @GetMapping("/animation/{id}")
    public List<AnimationController> getAnimationControllersByAnimation(@PathVariable Long id) {
        return animationControllerService.getAnimationControllersByAnimation(id);
    }

    @PostMapping("/{modelId}/{animationId}")
    public AnimationController createAnimationController(@PathVariable Long modelId, @PathVariable Long animationId, @RequestBody AnimationControllerRequestBody requestBody) {
        return animationControllerService.createAnimationController(modelId, animationId, requestBody);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimationController(@PathVariable Long id) {
        animationControllerService.deleteAnimationController(id);
    }

    @DeleteMapping("/animation/{id}")
    public void deleteAnimationControllersByModel(@PathVariable Long id) {
        animationControllerService.deleteAnimationControllersByAnimation(id);
    }
}
