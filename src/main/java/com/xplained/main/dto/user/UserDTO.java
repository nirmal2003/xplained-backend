package com.xplained.main.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private Integer type;
    private String email;
    private String username;
    private String image;
    private String description;
    private String heading;

    private String linkedin;
    private String youtube;
    private String web;
    private String otherUrl;
}
