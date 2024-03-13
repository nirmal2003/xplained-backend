package com.xplained.main.models.objects.animations;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.models.objects.animations.AnimationRequestBody;
import com.xplained.main.dto.models.objects.animations.AnimationResponse;
import com.xplained.main.dto.user.UserDTO;
import com.xplained.main.models.objects.EditorObject;
import com.xplained.main.models.objects.EditorObjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AnimationService {
    private final AnimationRepository animationRepository;
    private final EditorObjectRepository editorObjectRepository;
    private final AuthService authService;


    public List<AnimationResponse> getAllAnimationsByModel(Long modelId) {
        Pageable pageable = PageRequest.of(0, 20);
        return animationRepository.findAllByModelIdOrderByCreatedAt(modelId, pageable);
    }

    public List<AnimationResponse> getAllAnimationsByShape(Long objectId) {
        Pageable pageable = PageRequest.of(0, 20);
        return animationRepository.findAllByObjectIdOrderByCreatedAt(objectId, pageable);
    }

    public AnimationResponse createAnimation(Long shapeId, AnimationRequestBody requestBody) {
        EditorObject object = editorObjectRepository.findById(shapeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found"));

        Animation animation = animationRepository.saveAndFlush(Animation.builder()
                .objectId(object.getId())
                .modelId(object.getModelId())
                .type(requestBody.getType())
                .start(requestBody.getStart())
                .finish(requestBody.getEnd())
                .duration(requestBody.getDuration())
                .angle(requestBody.getAngle())
                .xAxis(requestBody.getXAxis())
                .yAxis(requestBody.getYAxis())
                .build());

        return AnimationResponse.builder()
                .id(animation.getId())
                .objectId(animation.getObjectId())
                .type(animation.getType())
                .start(animation.getStart())
                .end(animation.getFinish())
                .duration(animation.getDuration())
                .angle(animation.getAngle())
                .createdAt(animation.getCreatedAt())
                .left(animation.getXAxis())
                .top(animation.getYAxis())
                .build();
    }

    public void updateAnimation(Long id, AnimationRequestBody requestBody) {
        UserDTO user = authService.getCurrentUser();

        Animation animation = animationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animation not found"));
        EditorObject object = editorObjectRepository.findById(animation.getObjectId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found"));


        System.out.println(user);
        System.out.println(object);

        if (!object.getUserId().equals(user.getId()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Permission denied");

        if (requestBody.getType() != null) animation.setType(requestBody.getType());
        if (requestBody.getStart() != null) animation.setStart(requestBody.getStart());
        if (requestBody.getEnd() != null) animation.setFinish(requestBody.getEnd());
        if (requestBody.getDuration() != null) animation.setDuration(requestBody.getDuration());
        if (requestBody.getAngle() != null) animation.setAngle(requestBody.getAngle());
        if (requestBody.getXAxis() != null) animation.setXAxis(requestBody.getXAxis());
        if (requestBody.getYAxis() != null) animation.setYAxis(requestBody.getYAxis());

        animationRepository.save(animation);
    }

    public void deleteAnimation(Long id) {
        UserDTO user = authService.getCurrentUser();

        Animation animation = animationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animation not found"));
        EditorObject object = editorObjectRepository.findById(animation.getObjectId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found"));

        if (!object.getUserId().equals(user.getId()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Permission denied");

        animationRepository.delete(animation);
    }
}
