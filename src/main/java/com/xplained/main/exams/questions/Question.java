package com.xplained.main.exams.questions;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Question {
    @Id
    @GeneratedValue
    private Long id;
    private Long examId;

    @Column(columnDefinition = "TEXT")
    private String question;
    private Integer type;
    private Integer index;
    private Integer realIndex;
    private Float marks;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
