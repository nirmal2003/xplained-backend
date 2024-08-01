package com.xplained.main.institutions.users.requests;

import com.xplained.main.dto.institutions.users.requests.InstitutionUserRequestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/institutions/users/requests")
@RequiredArgsConstructor
public class InstitutionUserRequestController {
    private final InstitutionUserRequestService institutionUserRequestService;


    @GetMapping
    public List<InstitutionUserRequestResponse> getUserRequest() {
        return institutionUserRequestService.getUserRequest();
    }

    @PostMapping
    public InstitutionUserRequestResponse createUserRequest() {
        return institutionUserRequestService.createUserRequest();
    }

    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable Long id) {
        institutionUserRequestService.deleteRequest(id);
    }
}
