package com.xplained.main.dto.exams.question;

import lombok.Data;

import java.util.List;

@Data
public class IdAndIndexRequestBody {
    private List<IdAndIndex> data;
}
