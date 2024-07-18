package com.xplained.main.sliders.slides.elements.users;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.sliders.slides.elements.users.ElementUserRequestBody;
import com.xplained.main.sliders.slides.elements.ElementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElementUserService {
    private final ElementUserRepository elementUserRepository;
    private final ElementRepository elementRepository;
    private final AuthService authService;


    public ElementUser getElement(Long elementId) {
        return elementUserRepository.findByElementId(elementId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "element answer not found"));
    }

    public void updateUserElement(Long elementId, ElementUserRequestBody requestBody) {
        if (!elementRepository.existsById(elementId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Element not found");

        Optional<ElementUser> element = elementUserRepository.findByElementIdAndUserId(elementId, authService.getCurrentUser().getId());

        if (element.isPresent()) {

            element.get().setData(requestBody.getData());

            elementUserRepository.save(element.get());

        } else {
            ElementUser elementUser = ElementUser.builder()
                    .elementId(elementId)
                    .userId(authService.getCurrentUser().getId())
                    .data(requestBody.getData())
                    .build();

            elementUserRepository.save(elementUser);
        }
    }
}
