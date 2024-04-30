package com.xplained.main.models.objects.groups;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<ObjectGroup, Long> {
    List<ObjectGroup> findAllByModelIdOrderByCreatedAtDesc(Long modelId);
    Long countByModelId(Long modelId);
}
