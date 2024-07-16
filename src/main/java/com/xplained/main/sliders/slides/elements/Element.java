package com.xplained.main.sliders.slides.elements;

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
public class Element {
    @Id
    @GeneratedValue
    private Long id;
    private Long slideId;
    private Integer type;
    private String time;

    @Column(columnDefinition = "TEXT")
    private String data;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
