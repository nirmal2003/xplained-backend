package com.xplained.main.sliders.slides.elements.users;

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
public class ElementUser {
    @Id
    @GeneratedValue
    private Long id;
    private Long elementId;
    private Long userId;

    @Column(columnDefinition = "TEXT")
    private String data;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
