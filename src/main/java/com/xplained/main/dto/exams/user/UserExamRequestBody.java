package com.xplained.main.dto.exams.user;

import lombok.Data;

import java.math.BigInteger;

@Data
public class UserExamRequestBody {
    private Integer currentIndex;
    private BigInteger duration;
}
