package com.xplained.main.models.objects.animations;


import com.xplained.main.dto.models.objects.animations.AnimationRequestBody;
import com.xplained.main.dto.models.objects.animations.AnimationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editor/animations")
@RequiredArgsConstructor
public class AnimationController {
    private final AnimationService animationService;

    @GetMapping("/models/{id}")
    public List<AnimationResponse> getAllAnimationsByModel(@PathVariable Long id) {
        return animationService.getAllAnimationsByModel(id);
    }

    @GetMapping("/objects/{id}")
    public List<AnimationResponse> getAllAnimationsByObject(@PathVariable Long id) {
        return animationService.getAllAnimationsByShape(id);
    }

    @PostMapping("/{objectId}")
    public AnimationResponse createAnimation(@PathVariable Long objectId, @RequestBody AnimationRequestBody requestBody) {
        return animationService.createAnimation(objectId, requestBody);
    }

    @PutMapping("/{id}")
    public void updateAnimation(@PathVariable Long id, @RequestBody AnimationRequestBody requestBody) {
        animationService.updateAnimation(id, requestBody);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimation(@PathVariable Long id) {
        animationService.deleteAnimation(id);
    }
}
