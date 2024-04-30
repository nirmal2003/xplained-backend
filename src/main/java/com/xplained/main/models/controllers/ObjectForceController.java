package com.xplained.main.models.controllers;

import com.xplained.main.dto.models.controllers.ForceControllerReqBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models/forces/controllers")
@RequiredArgsConstructor
public class ObjectForceController {
    private final ObjectForceService objectForceService;

    @GetMapping("/{modelId}")
    public List<ObjectForce> getModelForceControllers(@PathVariable Long modelId) {
        return objectForceService.getModelForceControllers(modelId);
    }

    @PostMapping("/{objectId}")
    public ObjectForce createForceController(@PathVariable Long objectId, @RequestBody ForceControllerReqBody requestBody) {
        return objectForceService.createForceController(objectId, requestBody);
    }

    @DeleteMapping("/{id}")
    public void deleteForceController(@PathVariable Long id) {
        objectForceService.deleteForceController(id);
    }
}
