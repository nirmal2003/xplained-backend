package com.xplained.main.models.controllers;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.models.controllers.ForceControllerReqBody;
import com.xplained.main.dto.user.UserDTO;
import com.xplained.main.models.ModelRepository;
import com.xplained.main.models.objects.EditorObject;
import com.xplained.main.models.objects.EditorObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObjectForceService {
    private final EditorObjectRepository editorObjectRepository;
    private final ObjectForceRepository objectForceRepository;
    private final ModelRepository modelRepository;
    private final AuthService authService;


    public List<ObjectForce> getModelForceControllers(Long modelId) {
        return objectForceRepository.findAllByModelIdOrderByCreatedAtAsc(modelId);
    }

    public ObjectForce createForceController(Long objectId, ForceControllerReqBody requestBody) {

        EditorObject object = editorObjectRepository.findById(objectId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Object not found"));

        ObjectForce objectForce = ObjectForce.builder()
                .modelId(object.getModelId())
                .objectId(objectId)
                .name(requestBody.getName())
                .force(requestBody.getForce())
                .min(requestBody.getMin())
                .max(requestBody.getMax())
                .defaultValue(requestBody.getDefaultValue())
                .build();

        return objectForceRepository.save(objectForce);
    }

    public void deleteForceController(Long id) {
        UserDTO user = authService.getCurrentUser();

        ObjectForce objectForce = objectForceRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Force controller not found"));

        if (modelRepository.existsByIdAndUserId(objectForce.getModelId(), user.getId())) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Permission denied");

        objectForceRepository.delete(objectForce);
    }
}
