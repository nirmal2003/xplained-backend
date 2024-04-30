package com.xplained.main.models;

import com.xplained.main.dto.models.ModelRequestBody;
import com.xplained.main.dto.models.ModelResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;


    @GetMapping
    public List<ModelResponse> getAllModels(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        return modelService.getAllModels(page);
    }

    @GetMapping("/{id}")
    public ModelResponse getModelDetails(@PathVariable Long id) {
        return modelService.getModelDetails(id);
    }

    @PostMapping
    public ModelResponse createModel(@Valid @RequestBody ModelRequestBody requestBody) {
        return modelService.createModel(requestBody);
    }

    @PutMapping("/{id}")
    public void renameModel(@PathVariable Long id, @Valid @RequestBody ModelRequestBody requestBody) {
        modelService.updateModel(id, requestBody);
    }

    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable Long id) {
        modelService.deleteModel(id);
    }
}
