package com.xplained.main.courses;

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
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String title;
    private String heading;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Long numberOfLessons;
    private Long numberOfModels;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
