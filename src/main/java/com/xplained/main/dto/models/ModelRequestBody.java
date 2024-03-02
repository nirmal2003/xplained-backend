package com.xplained.main.dto.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ModelRequestBody {
    @NotEmpty
    private String title;
}
