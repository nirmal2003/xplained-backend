package com.xplained.main.dto.institutions.users.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class InstitutionUserRequestResponse {
    private Long id;
    private String chars;
    private Integer numberOfUsers;
    private LocalDateTime createdAt;
}
