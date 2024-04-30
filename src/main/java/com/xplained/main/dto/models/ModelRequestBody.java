package com.xplained.main.dto.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ModelRequestBody {
    private String title;
    private Double gravity;
}
