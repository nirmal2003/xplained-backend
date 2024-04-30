package com.xplained.main.models.objects.animations;

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
public class Animation {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long objectId;
    private Long modelId;
    private Integer type;
    private Double start;
    private Double finish;
    private Double angle;
    private Double duration;
    private Double xAxis;
    private Double yAxis;
    private Integer loopCount;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
