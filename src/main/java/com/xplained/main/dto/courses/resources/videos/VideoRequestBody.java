package com.xplained.main.dto.courses.resources.videos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class VideoRequestBody {
    @NotEmpty
    private String name;

    @NotEmpty
    private String thumbnail;
}
