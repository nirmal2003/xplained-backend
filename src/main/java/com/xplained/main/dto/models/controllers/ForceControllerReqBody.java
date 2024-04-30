package com.xplained.main.dto.models.controllers;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ForceControllerReqBody {

    @NotEmpty
    private String name;

    @NotEmpty
    @Size(min = 1, max = 7, message = "Unidentified force")
    private Integer force;

    @NotEmpty
    private Float min;

    @NotEmpty
    private Float max;

    @NotEmpty
    private Float defaultValue;
}
