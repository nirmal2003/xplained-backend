package com.xplained.main.dto.exams;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ExamRequestBody {
    private String title;
    private String image;
    private Boolean isTextEnabled;
    private BigInteger duration;
    private Boolean isPublished;
}
