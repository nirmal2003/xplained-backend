package com.xplained.main.dto.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModelResponse {
    private Long id;
    private String title;
    private Double gravity;
    private LocalDateTime createdAt;
}
