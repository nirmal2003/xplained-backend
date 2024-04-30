package com.xplained.main.dto.models.objects.collisions;

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
//@Entity
//@Table
public class CollisionGroups {
//    @Id
//    @GeneratedValue
    private Long id;
    private Integer objectType;
}
