package com.xplained.main.models.objects;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.models.objects.EditorObjectRequestBody;
import com.xplained.main.dto.models.objects.EditorObjectResponse;
import com.xplained.main.dto.user.UserDTO;
import com.xplained.main.models.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class EditorObjectService {
    private final AuthService authService;
    private final EditorObjectRepository editorObjectRepository;
    private final ModelRepository modelRepository;


    public List<EditorObjectResponse> getAllObjects(Long modelId) {
        return editorObjectRepository.findAllByModelIdOrderByCreatedAt(modelId);
    }

    public EditorObjectResponse createObject(Long modelId, EditorObjectRequestBody requestBody) {
        if (!modelRepository.existsById(modelId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Model not found");

        UserDTO user = authService.getCurrentUser();

        EditorObject object = editorObjectRepository.saveAndFlush(EditorObject.builder()
                .name(requestBody.getName())
                .userId(user.getId())
                .modelId(modelId)
                .fill(requestBody.getFill())
                .yAxis(ThreadLocalRandom.current().nextDouble(0, 100))
                .xAxis(ThreadLocalRandom.current().nextDouble(0, 500))
                .width(ThreadLocalRandom.current().nextDouble(50, 100))
                .height(ThreadLocalRandom.current().nextDouble(50, 100))
                .angle(0.0)
                .shape(requestBody.getShape())
                .radius(ThreadLocalRandom.current().nextDouble(50, 60))
                .isFrozen(true)
                .build());

        return EditorObjectResponse.builder()
                .id(object.getId())
                .name(object.getName())
                .fill(object.getFill())
                .yAxis(object.getYAxis())
                .xAxis(object.getXAxis())
                .width(object.getWidth())
                .height(object.getHeight())
                .angle(object.getAngle())
                .shape(object.getShape())
                .radius(object.getRadius())
                .isFrozen(object.getIsFrozen())
                .createdAt(object.getCreatedAt())
                .build();
    }

    public void updateObject(Long id, EditorObjectRequestBody requestBody) {
        EditorObject object = editorObjectRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found"));

        if (requestBody.getName()!= null) object.setName(requestBody.getName());
        if (requestBody.getShape() != null) object.setShape(requestBody.getShape());
        if (requestBody.getFill() != null) object.setFill(requestBody.getFill());
        if (requestBody.getWidth() != null) object.setWidth(requestBody.getWidth());
        if (requestBody.getHeight() != null) object.setHeight(requestBody.getHeight());
        if (requestBody.getTop() != null) object.setYAxis(requestBody.getTop());
        if (requestBody.getLeft() != null) object.setXAxis(requestBody.getLeft());
        if (requestBody.getAngle() != null) object.setAngle(requestBody.getAngle());
        if (requestBody.getRadius() != null) object.setRadius(requestBody.getRadius());
        if (requestBody.getIsFrozen() != null) object.setIsFrozen(requestBody.getIsFrozen());

        editorObjectRepository.save(object);
    }

    public void groupObject(List<Long> objects) {
        List<EditorObject> objectList = new ArrayList<>();

        for (Long objectId : objects) {
            Optional<EditorObject> editorObject = editorObjectRepository.findById(objectId);

            editorObject.ifPresent(objectList::add);
        }
    }

    public void deleteObject(Long id) {
        UserDTO user = authService.getCurrentUser();

        EditorObject object = editorObjectRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found"));

        editorObjectRepository.delete(object);
    }
}
