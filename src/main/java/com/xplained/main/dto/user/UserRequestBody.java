package com.xplained.main.dto.user;

import lombok.Data;

@Data
public class UserRequestBody {
    private String image;
    private String name;
    private String description;
    private String heading;

    private String linkedin;
    private String youtube;
    private String web;
    private String otherUrl;
}
