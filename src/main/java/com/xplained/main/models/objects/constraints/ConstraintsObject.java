package com.xplained.main.models.objects.constraints;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class ConstraintsObject {
    @Id
    @GeneratedValue
    private Long id;
    private Long objectId;
    private Long constraintId;
}
