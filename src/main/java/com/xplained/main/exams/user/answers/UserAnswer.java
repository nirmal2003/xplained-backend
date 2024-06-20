package com.xplained.main.exams.user.answers;

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
public class UserAnswer {
    @Id
    @GeneratedValue
    private Long id;
    private Long userExamId;
    private Long questionId;
    private Integer type;
    private String textAnswer;
    private Long mcqAnswer;
    private Boolean isCorrect;
    private Boolean isReviewed;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
