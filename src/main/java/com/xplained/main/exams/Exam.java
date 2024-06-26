package com.xplained.main.exams;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Exam {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private String title;
    private String image;
    private Boolean isTextEnabled;
    private BigInteger duration;

    private Boolean isPublished;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
