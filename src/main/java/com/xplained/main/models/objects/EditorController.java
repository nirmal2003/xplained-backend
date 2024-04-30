package com.xplained.main.models.objects;

import com.xplained.main.dto.models.objects.EditorObjectRequestBody;
import com.xplained.main.dto.models.objects.EditorObjectResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editor/objects")
@RequiredArgsConstructor
public class EditorController {
    private final EditorObjectService editorObjectService;



    @GetMapping("/models/{modelId}")
    public List<EditorObjectResponse> getAllModels(@PathVariable Long modelId) {
        return editorObjectService.getAllObjects(modelId);
    }

    @GetMapping("/{id}")
    public EditorObjectResponse getObjectDetails(@PathVariable Long id) {
        return editorObjectService.getObjectDetails(id);
    }

    @PostMapping("/{modelId}")
    public EditorObjectResponse createObject(@PathVariable Long modelId, @RequestBody EditorObjectRequestBody requestBody) {
        return editorObjectService.createObject(modelId, requestBody);
    }

    @PutMapping("/{id}")
    public void updateObject(@PathVariable Long id, @RequestBody EditorObjectRequestBody requestBody) {
        editorObjectService.updateObject(id, requestBody);
    }

    @DeleteMapping("/{id}")
    public void deleteObject(@PathVariable Long id) {
        editorObjectService.deleteObject(id);
    }
}
