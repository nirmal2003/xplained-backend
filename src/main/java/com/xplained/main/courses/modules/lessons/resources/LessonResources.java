package com.xplained.main.courses.modules.lessons.resources;

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
public class LessonResources {
    @Id
    @GeneratedValue
    private Long id;
    private Long lessonId;
    private Integer type;
    private Long video;
    private String name;
    private Long sliderId;
    private Long examId;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
