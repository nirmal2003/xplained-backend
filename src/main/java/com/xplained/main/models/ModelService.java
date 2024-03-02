package com.xplained.main.models;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.models.ModelRequestBody;
import com.xplained.main.dto.models.ModelResponse;
import com.xplained.main.dto.user.UserDTO;
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
    private final ModelRepository modelRepository;
    private final AuthService authService;


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
                .build());

        return ModelResponse.builder()
                .id(model.getId())
                .title(model.getTitle())
                .createdAt(model.getCreatedAt())
                .build();
    }

    public void renameModel(Long id, ModelRequestBody requestBody) {
        UserDTO user = authService.getCurrentUser();

        Model model = modelRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Model not found"));

        model.setTitle(requestBody.getTitle());

        modelRepository.save(model);
    }

    public void deleteModel(Long id) {
        UserDTO user = authService.getCurrentUser();

        Model model = modelRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Model not found"));

        modelRepository.delete(model);
    }
}
