package com.xplained.main.institutions.users.requests;

import com.xplained.main.auth.AuthService;
import com.xplained.main.dto.institutions.users.requests.InstitutionUserRequestResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class InstitutionUserRequestService {
    private final InstitutionUserRequestRepository institutionUserRequestRepository;
    private final AuthService authService;


    public List<InstitutionUserRequestResponse> getUserRequest() {
        return institutionUserRequestRepository.findAllByInstitutionId(authService.getCurrentUser().getId());
    }

    public InstitutionUserRequestResponse createUserRequest() {
        InstitutionUserRequest request = institutionUserRequestRepository.saveAndFlush(InstitutionUserRequest.builder()
                .institutionId(authService.getCurrentUser().getId())
                .chars(UUID.randomUUID().toString())
                .build());

        return InstitutionUserRequestResponse.builder()
                .id(request.getId())
                .chars(request.getChars())
                .numberOfUsers(0)
                .createdAt(request.getCreatedAt())
                .build();
    }

    public void deleteRequest(Long id) {
        InstitutionUserRequest request = institutionUserRequestRepository.findByIdAndInstitutionId(id, authService.getCurrentUser().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "no requests found"));

        institutionUserRequestRepository.delete(request);
    }
}
