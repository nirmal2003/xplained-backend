package com.xplained.main.models.objects.animations;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimationRepository extends JpaRepository<Animation, Long> {
    List<Animation> findAllByObjectIdOrderByCreatedAt(Long shapeId, Pageable pageable);
    List<Animation> findAllByModelIdOrderByCreatedAt(Long modelId, Pageable pageable);
}
