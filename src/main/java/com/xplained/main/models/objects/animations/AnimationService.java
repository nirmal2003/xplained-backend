package com.xplained.main.models.objects.animations;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.models.objects.animations.AnimationRequestBody;
import com.xplained.main.dto.models.objects.animations.AnimationResponse;
import com.xplained.main.dto.user.UserDTO;
import com.xplained.main.models.objects.EditorObject;
import com.xplained.main.models.objects.EditorObjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@RequiredArgsConstructor
public class AnimationService {
    private final AnimationRepository animationRepository;
    private final EditorObjectRepository editorObjectRepository;
    private final AuthService authService;


    public AnimationResponse createAnimation(Long shapeId, AnimationRequestBody requestBody) {
        EditorObject object = editorObjectRepository.findById(shapeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found"));

        Animation animation = animationRepository.saveAndFlush(Animation.builder()
                .objectId(object.getId())
                .modelId(object.getModelId())
                .type(requestBody.getType())
                .start(requestBody.getStart())
                .end(requestBody.getEnd())
                .duration(requestBody.getDuration())
                .build());

        return AnimationResponse.builder()
                .id(animation.getId())
                .type(animation.getType())
                .start(animation.getStart())
                .end(animation.getEnd())
                .duration(animation.getDuration())
                .createdAt(animation.getCreatedAt())
                .build();
    }

    public void deleteAnimation(Long id) {
        UserDTO user = authService.getCurrentUser();

        Animation animation = animationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animation not found"));
        EditorObject object = editorObjectRepository.findById(animation.getObjectId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found"));

        if (object.getUserId().equals(user.getId())) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Permission denied");

        animationRepository.delete(animation);
    }
}
