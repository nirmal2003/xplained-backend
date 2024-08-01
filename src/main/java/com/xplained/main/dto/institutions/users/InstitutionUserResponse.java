package com.xplained.main.dto.institutions.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InstitutionUserResponse {
    private Long id;
    private Long userid;
    private String username;
    private String userImage;
}
