package com.xplained.main.models.objects.groups.objects;

import com.xplained.main.models.objects.EditorObject;
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
public class GroupObject {
    @Id
    @GeneratedValue
    private Long id;
    private Long modelId;
    private Long groupId;
    private Long objectId;

    @Transient
    private EditorObject object;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
