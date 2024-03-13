package com.xplained.main.models.objects.animations.controllers;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.models.objects.animations.controller.AnimationControllerRequestBody;
import com.xplained.main.dto.user.UserDTO;
import com.xplained.main.models.Model;
import com.xplained.main.models.ModelRepository;
import com.xplained.main.models.objects.animations.Animation;
import com.xplained.main.models.objects.animations.AnimationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AnimationControllerService {
    private final AnimationControllerRepository animationControllerRepository;
    private final ModelRepository modelRepository;
    private final AnimationRepository animationRepository;
    private final AuthService authService;


    public List<AnimationController> getAnimationControllersByModel(Long id) {
        return animationControllerRepository.findAllByModelId(id);
    }

    public List<AnimationController> getAnimationControllersByAnimation(Long id) {
        return animationControllerRepository.findAllByAnimationId(id);
    }

    public AnimationController createAnimationController(Long modelId, Long animationId, AnimationControllerRequestBody requestBody) {
        if (!modelRepository.existsById(modelId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Model not found");
        if (!animationRepository.existsById(animationId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animation not found");

        AnimationController controller = AnimationController.builder()
                .modelId(modelId)
                .animationId(animationId)
                .type(requestBody.getType())
                .controller(requestBody.getController())
                .build();

        return animationControllerRepository.saveAndFlush(controller);
    }

    public void deleteAnimationController(Long id) {
        UserDTO user = authService.getCurrentUser();

        AnimationController controller = animationControllerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animation not found"));
        if (!modelRepository.existsByIdAndUserId(controller.getModelId(), user.getId())) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Model not found");

        animationControllerRepository.delete(controller);
    }


    public void deleteAnimationControllersByAnimation(Long id) {
        UserDTO user = authService.getCurrentUser();

        System.out.println(id);

//        if (!animationRepository.existsByIdAndUserId(id, user.getId())) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animation not found");

        animationControllerRepository.deleteAllByAnimationId(id);
    }
}
