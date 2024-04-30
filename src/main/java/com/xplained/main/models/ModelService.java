package com.xplained.main.models;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.models.ModelRequestBody;
import com.xplained.main.dto.models.ModelResponse;
import com.xplained.main.dto.user.UserDTO;
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
public class ModelService {
    private final AuthService authService;
    private final ModelRepository modelRepository;
    private final EditorObjectRepository editorObjectRepository;


    public List<ModelResponse> getAllModels(Integer page) {
        UserDTO user = authService.getCurrentUser();

        Pageable pageable = PageRequest.of(page, 20);

        return modelRepository.findAllByUserIdOrderByCreatedAtDesc(user.getId(), pageable);
    }

    public ModelResponse createModel(ModelRequestBody requestBody) {
        UserDTO user = authService.getCurrentUser();

        Model model = modelRepository.saveAndFlush(Model.builder()
                .userId(user.getId())
                .title(requestBody.getTitle())
                .gravity(1.0)
                .build());

        return ModelResponse.builder()
                .id(model.getId())
                .title(model.getTitle())
                .createdAt(model.getCreatedAt())
                .build();
    }

    public ModelResponse getModelDetails(Long id) {
        Model model = modelRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Model not found"));

        return ModelResponse.builder()
                .id(model.getId())
                .title(model.getTitle())
                .gravity(model.getGravity())
                .createdAt(model.getCreatedAt())
                .build();
    }

    public void updateModel(Long id, ModelRequestBody requestBody) {
        System.out.println(requestBody);
        UserDTO user = authService.getCurrentUser();

        Model model = modelRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Model not found"));

        if (requestBody.getTitle() != null) model.setTitle(requestBody.getTitle());
        if (requestBody.getGravity() != null) model.setGravity(requestBody.getGravity());


        modelRepository.save(model);
    }

    public void deleteModel(Long id) {
        UserDTO user = authService.getCurrentUser();

        Model model = modelRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Model not found"));

        editorObjectRepository.deleteByModelId(model.getId());

        modelRepository.delete(model);
    }
}
