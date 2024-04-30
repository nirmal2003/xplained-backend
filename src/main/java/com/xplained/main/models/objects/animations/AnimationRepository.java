package com.xplained.main.models.objects.animations;

import com.xplained.main.dto.models.objects.animations.AnimationResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimationRepository extends JpaRepository<Animation, Long> {

    @Query("SELECT new com.xplained.main.dto.models.objects.animations.AnimationResponse(a.id, a.name, a.objectId, a.type, a.start, a.finish, a.duration, a.angle, a.xAxis, a.yAxis, a.loopCount, a.createdAt) FROM Animation a WHERE a.objectId = :objectId ORDER BY a.createdAt DESC")
    List<AnimationResponse> findAllByObjectIdOrderByCreatedAt(@Param("objectId") Long objectId, Pageable pageable);

    @Query("SELECT new com.xplained.main.dto.models.objects.animations.AnimationResponse(a.id, a.name, a.objectId, a.type, a.start, a.finish, a.duration, a.angle, a.xAxis, a.yAxis, a.loopCount, a.createdAt) FROM Animation a WHERE a.modelId = :modelId ORDER BY a.createdAt DESC")
    List<AnimationResponse> findAllByModelIdOrderByCreatedAt(@Param("modelId") Long modelId, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Animation a JOIN Model m ON a.modelId = m.id WHERE a.id = :id AND m.userId = :userId")
    Boolean existsByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);

    Long countByModelId(Long modelId);
}
