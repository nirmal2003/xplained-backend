package com.xplained.main.sliders.slides.elements.users;

import com.xplained.main.dto.sliders.slides.elements.users.ElementUserRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sliders/slides/elements/users")
@RequiredArgsConstructor
public class ElementUserController {
    private final ElementUserService elementUserService;


    @GetMapping("/{elementId}")
    public ElementUser getElement(@PathVariable Long elementId) {
        return elementUserService.getElement(elementId);
    }

    @PutMapping("/{elementId}")
    public void updateUserElement(@PathVariable Long elementId, @RequestBody ElementUserRequestBody requestBody) {
        elementUserService.updateUserElement(elementId, requestBody);
    }
}
